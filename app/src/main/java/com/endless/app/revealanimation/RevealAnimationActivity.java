package com.endless.app.revealanimation;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;

import com.endless.app.R;

/**
 * 显示动画
 * @author haosiyuan
 * @date 2019/4/4 9:05 AM
 */
public class RevealAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    boolean flag;
    private FloatingActionButton fab;
    private View mPuppet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reveal_anim);

        initToolbar();

        mPuppet = findViewById(R.id.view_puppet);
        flag = mPuppet.getVisibility() == View.VISIBLE;

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    /**
     * 点击
     * @param v
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        Animation animation = mPuppet.getAnimation();
        if (animation != null) {
            animation.cancel();
        }

        int[] vLocation = new int[2];
        fab.getLocationInWindow(vLocation);
        //fab中心
        int centerX = vLocation[0] + fab.getWidth() / 2;
        int centerY = vLocation[1] + fab.getHeight() / 2;

        View view = findViewById(R.id.toolbar_layout);
        //斜线距离
        int hypotenuse = (int) Math.hypot(view.getWidth(), view.getHeight());

        if (flag) {
            final Animator circularReveal =  ViewAnimationUtils.createCircularReveal(mPuppet, centerX, centerY, hypotenuse, 0);
            circularReveal.setDuration(2000);
            circularReveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mPuppet.setVisibility(View.GONE);
                    circularReveal.removeListener(this);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            circularReveal.start();
            flag = false;
        } else {
            final Animator circularReveal =  ViewAnimationUtils.createCircularReveal(mPuppet, centerX, centerY, 0, hypotenuse);
            circularReveal.setDuration(2000);
            circularReveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    circularReveal.removeListener(this);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            mPuppet.setVisibility(View.VISIBLE);
            circularReveal.start();
            flag = true;
        }

    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reveal_animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.action_jump) {
            startActivity(new Intent(RevealAnimationActivity.this, RevealEffectActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
