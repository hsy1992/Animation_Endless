package com.endless.app.transition;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.endless.app.R;
import com.endless.app.transition.adapter.GridSpacingItemDecoration;
import com.endless.app.transition.adapter.MyRecyclerViewAdapter;

/**
 * 转场
 * @author haosiyuan
 * @date 2019/4/4 11:11 AM
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class TransitionAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transition_animation);

        setupWindowAnimations();

        initToolbar();

        initTransitionsView();

        initRecyclerView();
    }

    /**
     * 逐渐显示的进场动画
     */
    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
    }

    private void initTransitionsView() {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.viewgroup_transition);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setOnClickListener(this);
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rv_share_elements);
        recyclerView.setFocusable(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 5, false));
        recyclerView.setAdapter(new MyRecyclerViewAdapter(this));
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            //If you use 'finish();' you will not get the animation effect,
            //you can use the following methods instead.
            supportFinishAfterTransition();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 返回动画
     * @return
     */
    private Visibility buildReturnTransition() {
        Visibility enterTransition = new Slide();
        enterTransition.setDuration(500);
        return enterTransition;
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {

    }
}
