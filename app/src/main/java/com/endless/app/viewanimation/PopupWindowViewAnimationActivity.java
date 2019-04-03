package com.endless.app.viewanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.endless.app.R;

/**
 * 为PopupWindow 添加动画
 * @author haosiyuan
 * @date 2019/4/3 11:10 AM
 */
public class PopupWindowViewAnimationActivity extends AppCompatActivity {

    private PopupWindow mImgPopupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popupwindow);

        initToolbar();

        findViewById(R.id.btn_show_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImgPopupWindow(v);
            }
        });

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
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showImgPopupWindow(View anchor) {
        if (mImgPopupWindow == null) {
            ImageView view = new ImageView(this);
            view.setImageDrawable(getResources().getDrawable(R.drawable.img_popup));

            mImgPopupWindow = new PopupWindow(view, anchor.getMeasuredWidth(), anchor.getMeasuredWidth());
            mImgPopupWindow.setAnimationStyle(R.style.popup_anim_style);
        }

        if (mImgPopupWindow.isShowing()) {
            mImgPopupWindow.dismiss();
        } else {
            mImgPopupWindow.showAsDropDown(anchor);
        }
    }
}
