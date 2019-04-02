package com.endless.app.transition;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import com.endless.app.R;

/**
 * @author haosiyuan
 * @date 2019/4/2 2:59 PM
 */
public class TestA extends Activity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);

        int position = getIntent().getIntExtra("position", -1);

        setTransition(position);

        setContentView(R.layout.activity_test_a);

        TextView textView = findViewById(R.id.textView);

    }

    /**
     * 转场动画
     * "EnterTransition", "ReturnTransition"
     * @param position
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setTransition(int position) {

        //滑动
        Slide slide = new Slide();
        slide.setDuration(2000);
        slide.setSlideEdge(Gravity.RIGHT);
        //延迟开始 slide.setStartDelay();
        //出现 只支持出现视图或者 只支持消失视图 slide.setMode();
        //中心点 slide.setEpicenterCallback();
        //插值器 slide.setInterpolator();
        //褪去
        Fade fade = new Fade();
        fade.setDuration(2000);
        //下放插入
        Explode explode = new Explode();
        explode.setDuration(2000);

        switch (position) {
            case 0:
                getWindow().setEnterTransition(slide);
                break;
            case 1:
                getWindow().setEnterTransition(fade);
                break;
            case 2:
                getWindow().setEnterTransition(explode);
                break;
            case 3:
                getWindow().setReturnTransition(slide);
                break;
            case 4:
                getWindow().setReturnTransition(fade);
                break;
            case 5:
                getWindow().setReturnTransition(explode);
                break;
            default:
                break;
        }
    }
}
