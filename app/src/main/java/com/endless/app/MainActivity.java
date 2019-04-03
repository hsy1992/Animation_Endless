package com.endless.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.endless.app.drawableanim.DrawableAnimActivity;
import com.endless.app.propertyanim.PropertyAnimationActivity;
import com.endless.app.transition.TransitionActivity;
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
}
