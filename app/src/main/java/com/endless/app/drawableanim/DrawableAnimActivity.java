package com.endless.app.drawableanim;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.endless.app.R;

/**
 * Drawable 动画 帧动画
 * @author haosiyuan
 * @date 2019/4/3 1:59 PM
 */
public class DrawableAnimActivity extends AppCompatActivity {

    private ImageView mPuppet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawable_anim);

        initToolbar();

        mPuppet = findViewById(R.id.img_puppet);
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_on_off, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
        } else if (i == R.id.action_do) {
            doAnimation(getAnimationDrawable(false), true);
        } else if (i == R.id.action_stop) {
            doAnimation(getAnimationDrawable(true), false);
        }
        return super.onOptionsItemSelected(item);
    }

    private AnimationDrawable getAnimationDrawable(boolean fromXml) {

        if (fromXml) {
            AnimationDrawable drawable = (AnimationDrawable) mPuppet.getDrawable();
            return drawable;
        } else {
            AnimationDrawable animationDrawable = new AnimationDrawable();

            for (int i = 1; i < 8; i++) {

                int id = getResources().getIdentifier("run" + i,"drawable", getPackageName());
                Drawable drawable = getResources().getDrawable(id);
                if (drawable != null) {
                    animationDrawable.addFrame(drawable, 100);
                }
            }
            mPuppet.setImageDrawable(animationDrawable);
            return animationDrawable;
        }

    }

    private void doAnimation(AnimationDrawable animationDrawable, boolean doIt) {
        if (animationDrawable.isRunning()) {
            animationDrawable.stop();
        }

        //When you want to restart the animation, stop the animation first.
        if (doIt) {
            animationDrawable.start();
        }
    }
}
