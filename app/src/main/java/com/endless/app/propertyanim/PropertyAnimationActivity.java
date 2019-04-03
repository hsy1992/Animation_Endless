package com.endless.app.propertyanim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

import com.endless.app.R;

import java.util.ArrayList;

/**
 * 属性动画
 * @author haosiyuan
 * @date 2019/4/3 2:29 PM
 */
public class PropertyAnimationActivity extends AppCompatActivity {

    private View mPuppet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_property_animation);

        mPuppet = findViewById(R.id.view_puppet);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_property, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
        } else if (i == R.id.action_do_byxml) {
            doAnimation(getAnimationDrawable(true));
        } else if (i == R.id.action_bycode) {
            doAnimation(getAnimationDrawable(false));
        } else if (i == R.id.action_bycustom) {
            doAnimation(getValueAnimatorByCustom());
        } else if (i == R.id.action_byviewpropertyanimator) {
            doAnimatorByViewPropertyAnimator();
        } else if (i == R.id.action_bylayoutanimator) {
            doLayoutAnimator();
        }
        return super.onOptionsItemSelected(item);
    }

    private Animator getValueAnimatorByCustom() {

        final int height = mPuppet.getLayoutParams().height;
        final int width = mPuppet.getLayoutParams().width;
        PropertyBean startPropertyBean = new PropertyBean(0xff009688, 0f, 1f);
        PropertyBean endPropertyBean = new PropertyBean(0xff795548, 360f, 3.0f);

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new MyTypeEvaluator(), startPropertyBean, endPropertyBean);
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new SpeedUpInterpolator());
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(1);
        valueAnimator.setObjectValues(startPropertyBean, endPropertyBean);
        valueAnimator.setEvaluator(new MyTypeEvaluator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PropertyBean propertyBean = (PropertyBean) valueAnimator.getAnimatedValue();
                if (propertyBean.getBackgroundColor() != 0 && propertyBean.getBackgroundColor() != 1) {
                    mPuppet.setBackgroundColor(propertyBean.getBackgroundColor());
                }
                mPuppet.setRotationX(propertyBean.getRotationX());
                mPuppet.getLayoutParams().height = (int) (height * propertyBean.getSize());
                mPuppet.getLayoutParams().width = (int) (width * propertyBean.getSize());
                mPuppet.requestLayout();
            }
        });
        return valueAnimator;
    }


    private Animator getAnimationDrawable(boolean fromXml) {
        return fromXml ? getAnimationByXml() : getAnimatorSet();
    }

    /**
     * 代码实现
     * @return
     */
    private Animator getAnimatorSet() {
        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.playTogether(getObjectAnimatorByPropertyValuesHolder(), getValueAnimator());

        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        return animatorSet;
    }

    /**
     * xml 的 property animation
     * @return
     */
    private Animator getAnimationByXml() {
        final int height = mPuppet.getLayoutParams().height;
        final int width = mPuppet.getLayoutParams().width;
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animation_property);

        //获取集合子动画
        ArrayList<Animator> childAnimations = animatorSet.getChildAnimations();

        if (childAnimations.size() == 0) {
            return null;
        }
        //根据最后一个 animator 做出改变 赋值动画
        ((ValueAnimator) childAnimations.get(childAnimations.size() - 1))
                .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        //根据赋值动画 修改Layout
                        float animatedValue = (float) animation.getAnimatedValue();
                        mPuppet.getLayoutParams().height = (int) (height * animatedValue);
                        mPuppet.getLayoutParams().width = (int) (width * animatedValue);
                        mPuppet.requestLayout();
                    }
                });

        animatorSet.setTarget(mPuppet);
        return animatorSet;
    }

    private void doLayoutAnimator() {
        LayoutTransition layoutTransition = new LayoutTransition();

        layoutTransition.setAnimator(LayoutTransition.APPEARING, getObjectAnimator(false));
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, getObjectAnimator(true));
        layoutTransition.setDuration(2000);

        //mPuppet's parentView
        ViewGroup contentView = (ViewGroup) ((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
        contentView.setLayoutTransition(layoutTransition);
        if (contentView.findViewById(R.id.view_puppet) == null) {
            contentView.addView(mPuppet);
        } else {
            contentView.removeView(mPuppet);
        }
    }

    /**
     *
     * @param b
     * @return
     */
    public ObjectAnimator getObjectAnimator(boolean b) {
        if (b) {
            ObjectAnimator bgColorAnimator = ObjectAnimator.ofArgb(mPuppet,
                    "backgroundColor",
                    0xff009688, 0xff795548);
            bgColorAnimator.setRepeatCount(1);
            bgColorAnimator.setDuration(3000);
            bgColorAnimator.setRepeatMode(ValueAnimator.REVERSE);
            bgColorAnimator.setStartDelay(0);
            return bgColorAnimator;
        } else {
            ObjectAnimator rotationXAnimator = ObjectAnimator.ofFloat(mPuppet,
                    "rotationX",
                    0f, 360f);
            rotationXAnimator.setRepeatCount(1);
            rotationXAnimator.setDuration(3000);
            rotationXAnimator.setRepeatMode(ValueAnimator.REVERSE);
            return rotationXAnimator;
        }
    }

    private void doAnimatorByViewPropertyAnimator() {
        //直接调用
        ViewPropertyAnimator viewPropertyAnimator = mPuppet.animate()
                .rotationX(360f)
                .alpha(0.5f)
                .scaleX(3).scaleY(3)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(3000)
                .setStartDelay(0);

    }

    /**
     * 创建多个动画 {@link PropertyValuesHolder}创建属性动画
     * @return
     */
    public Animator getObjectAnimatorByPropertyValuesHolder() {
        // ArgbEvaluator
        PropertyValuesHolder bgHolder = PropertyValuesHolder.ofObject("backgroundColor",
                new ArgbEvaluator(), 0xff009688, 0xff795548);

        PropertyValuesHolder rotateXHolder = PropertyValuesHolder.ofFloat("rotationX",
                0f, 360f);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mPuppet, bgHolder, rotateXHolder);
        objectAnimator.setDuration(3000);
        objectAnimator.setRepeatCount(1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        return objectAnimator;
    }

    /**
     * 获得一个赋值动画
     * @return
     */
    public ValueAnimator getValueAnimator() {
        final int height = mPuppet.getLayoutParams().height;
        final int width = mPuppet.getLayoutParams().width;

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 3f);
        valueAnimator.setDuration(3000);
        valueAnimator.setRepeatCount(1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //根据赋值动画 修改Layout
                float animatedValue = (float) animation.getAnimatedValue();
                mPuppet.getLayoutParams().height = (int) (height * animatedValue);
                mPuppet.getLayoutParams().width = (int) (width * animatedValue);
                mPuppet.requestLayout();
            }
        });

        return valueAnimator;
    }

    /**
     * 开启动画
     * @param animationDrawable
     */
    private void doAnimation(Animator animationDrawable) {
        if (animationDrawable == null) {
            return;
        }

        animationDrawable.start();
    }
}
