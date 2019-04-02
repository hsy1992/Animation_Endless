package com.endless.app.transition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.endless.app.R;

/**
 * 页面转换动画
 * @author haosiyuan
 * @date 2019/4/2 2:52 PM
 */
public class TransitionActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTransition(0);
        setContentView(R.layout.activity_transition);

        listView = findViewById(R.id.listView);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.item_transition, new String[]{
                "EnterTransitionSlide", "EnterTransitionFade", "EnterTransitionExplode",
                "ReturnTransitionSlide", "ReturnTransitionFade", "ReturnTransitionExplode",
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(TransitionActivity.this, TestA.class);
        intent.putExtra("position", position);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    /**
     * 转场动画
     *  "ExitTransition", "ReenterTransition",
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
        //爆炸
        Explode explode = new Explode();
        explode.setDuration(2000);

        switch (position) {
            case 0:
                getWindow().setExitTransition(slide);
                getWindow().setReenterTransition(slide);
                break;
            case 1:
                getWindow().setExitTransition(fade);
                getWindow().setReenterTransition(fade);
                break;
            case 2:
                getWindow().setExitTransition(explode);
                getWindow().setReenterTransition(explode);
                break;
            default:
                break;
        }
    }
}
