package com.endless.app.viewanimation;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.endless.app.R;

/**
 * view 动画
 * @author haosiyuan
 * @date 2019/4/2 5:26 PM
 */
public class ViewAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        initToolbar();
    }

    public void onCommonViewAnimation(View view) {
        startActivity(new Intent(ViewAnimationActivity.this, CommonViewActivity.class));
        setTransition();
    }

    public void onPopupWindowViewAnimation(View view) {
        startActivity(new Intent(ViewAnimationActivity.this, PopupWindowViewAnimationActivity.class));
        setTransition();
    }

    public void onRecycleViewViewAnimation(View view) {
        startActivity(new Intent(ViewAnimationActivity.this, RecyclerViewActivity.class));
        setTransition();
    }


    /**
     * activity 切换动画
     */
    private void setTransition() {
        overridePendingTransition(R.anim.animation_activity_enter, R.anim.animation_activity_exit);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
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
