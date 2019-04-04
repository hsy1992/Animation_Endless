package com.endless.app.revealanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;

import com.endless.app.R;

/**
 * @author haosiyuan
 * @date 2019/4/4 10:02 AM
 */
public class RevealEffectActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean flag = false;
    private FloatingActionButton fab;
    private View mPuppet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reveal_effect);

        initToolbar();

        fab = findViewById(R.id.fab);
        mPuppet = findViewById(R.id.view_puppet);
        fab.setOnClickListener(this);
    }

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
        int centerX = vLocation[0] + fab.getMeasuredWidth() / 2;
        int centerY = vLocation[1] + fab.getMeasuredHeight() / 2;


        int height = mPuppet.getHeight();
        int width = mPuppet.getWidth();
        int maxRradius = (int) Math.hypot(height, width);

        Log.e("test", "maxRradius>>>>>>" + maxRradius);
        if (flag) {

            Animator animator = ViewAnimationUtils.createCircularReveal(mPuppet, centerX, centerY, maxRradius, 0);
            animator.setDuration(1000);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mPuppet.setVisibility(View.GONE);
                }
            });
            animator.start();
            flag = false;
        } else {
            final Animator animator = ViewAnimationUtils.createCircularReveal(mPuppet, centerX, centerY, 0, maxRradius);
            animator.setDuration(1000);
            animator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    mPuppet.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    animator.removeListener(this);
                }
            });

            animator.start();
            flag = true;
        }
    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
