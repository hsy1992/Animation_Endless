package com.endless.app.viewanimation;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.endless.app.R;

/**
 * view 的动画集合
 * @author haosiyuan
 * @date 2019/4/3 9:25 AM
 */
public class CommonViewActivity extends AppCompatActivity {

    private ImageView mPuppet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_view);

        initToolbar();
        mPuppet = findViewById(R.id.view_puppet);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
        } else if (i == R.id.action_alpha) {
            doAnimation(getAlphaAnimation());
        } else if (i == R.id.action_rotate) {
            doAnimation(getRotateAnimation());
        } else if (i == R.id.action_scale) {
            doAnimation(getScaleAnimation());
        } else if (i == R.id.action_translate) {
            doAnimation(getTranslateAnimation());
        } else if (i == R.id.action_set) {
            doAnimation(getAnimationSet());
        } else {
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 执行动画
     * @param animation
     */
    private void doAnimation(Animation animation) {
        Animation oldAnimation = mPuppet.getAnimation();
        if (oldAnimation != null) {
            if (oldAnimation.hasStarted() || (!oldAnimation.hasEnded())) {
                oldAnimation.cancel();
                mPuppet.clearAnimation();
            }
        }

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mPuppet.startAnimation(animation);
    }

    /**
     * 透明度
     * @return
     */
    private Animation getAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setFillBefore(false);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        return alphaAnimation;
    }

    /**
     * 旋转
     * @return
     */
    private Animation getRotateAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f,
                getWidth()/2, getHeight()/2);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(1);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setFillBefore(false);
        return rotateAnimation;
    }

    private Animation getAnimationSet() {
        AnimationSet innerAnimationSet = new AnimationSet(true);
        innerAnimationSet.setInterpolator(new BounceInterpolator());
        innerAnimationSet.setStartOffset(1000);
        innerAnimationSet.addAnimation(getScaleAnimation());
        innerAnimationSet.addAnimation(getTranslateAnimation());

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new LinearInterpolator());

        animationSet.addAnimation(getAlphaAnimation());
        animationSet.addAnimation(getRotateAnimation());
        animationSet.addAnimation(innerAnimationSet);

        return animationSet;
    }

    private Animation getTranslateAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                0, getWidth() * 2, 0,getHeight() *2
        );
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatCount(1);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillBefore(false);
        return translateAnimation;
    }

    private Animation getScaleAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 2f, 1f,2f, getWidth()/2,getHeight()/2);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setFillBefore(false);
        return scaleAnimation;
    }

    private int getWidth() {
        return mPuppet.getWidth();
    }

    private int getHeight() {
        return mPuppet.getHeight();
    }
}
