package com.endless.app.stateanimation;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.endless.app.R;

/**
 * 状态动画
 * @author haosiyuan
 * @date 2019/4/4 2:09 PM
 */
public class StateAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_state_anim);

        initToolbar();
        setStateListAnimator();
    }

    private void setStateListAnimator() {
        StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(this, R.animator.anim_view_state_change_2);
        findViewById(R.id.view_puppet2).setStateListAnimator(stateListAnimator);
    }

    private void initToolbar() {
        Toolbar toolbar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
