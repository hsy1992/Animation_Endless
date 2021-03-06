package com.endless.app;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.endless.app.drawableanim.DrawableAnimActivity;
import com.endless.app.propertyanim.PropertyAnimationActivity;
import com.endless.app.revealanimation.RevealAnimationActivity;
import com.endless.app.stateanimation.StateAnimationActivity;
import com.endless.app.touchanim.TouchAnimationActivity;
import com.endless.app.transition.TransitionActivity;
import com.endless.app.transition.TransitionAnimationActivity;
import com.endless.app.viewanimation.ViewAnimationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 跳转去页面跳转动画
     * @param view
     */
    public void onJumpActivityAnim(View view) {
        startActivity(new Intent(MainActivity.this, TransitionActivity.class));
    }

    /**
     * 跳转View Animation
     * @param view
     */
    public void onJumpViewAnimationAnim(View view) {
        startActivity(new Intent(MainActivity.this, ViewAnimationActivity.class));
    }

    /**
     * DrawableAnimation 帧动画
     * @param view
     */
    public void onJumpDrawableAnimation(View view) {
        startActivity(new Intent(MainActivity.this, DrawableAnimActivity.class));
    }

    public void onJumpPropertyAnimation(View view) {
        startActivity(new Intent(MainActivity.this, PropertyAnimationActivity.class));
    }

    public void onJumpTouchAnimation(View view) {
        startActivity(new Intent(MainActivity.this, TouchAnimationActivity.class));
    }

    public void onJumpRevealAnimation(View view) {
        startActivity(new Intent(MainActivity.this, RevealAnimationActivity.class));
    }

    public void onJumpTransitionAnimationActivityAnimation(View view) {
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((MainActivity.this), null);
        startActivity(new Intent(MainActivity.this, TransitionAnimationActivity.class), activityOptionsCompat.toBundle());
    }

    public void onJumpStateAnimationActivityAnimation(View view) {
        startActivity(new Intent(MainActivity.this, StateAnimationActivity.class));
    }
}
