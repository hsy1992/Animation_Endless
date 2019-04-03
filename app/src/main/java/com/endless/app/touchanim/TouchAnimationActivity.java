package com.endless.app.touchanim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.endless.app.R;

/**
 * 触摸动画
 * @author haosiyuan
 * @date 2019/4/3 5:26 PM
 */
public class TouchAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_anim);

        initToolbar();

    }

    private void initToolbar() {
    }
}
