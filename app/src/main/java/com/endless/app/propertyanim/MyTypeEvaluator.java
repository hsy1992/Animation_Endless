package com.endless.app.propertyanim;

import android.animation.ArgbEvaluator;
import android.animation.TypeEvaluator;


/**
 * 自定义解释器
 */
public class MyTypeEvaluator implements TypeEvaluator<PropertyBean> {
    ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();

    @Override
    public PropertyBean evaluate(float fraction, PropertyBean startPropertyBean, PropertyBean endPropertyBean) {
//        float currentColor = startPropertyBean.getBackgroundColor() + (endPropertyBean.getBackgroundColor() - startPropertyBean.getBackgroundColor()) * fraction;
        int currentColor = (int) mArgbEvaluator.evaluate(fraction, startPropertyBean.getBackgroundColor(), endPropertyBean.getBackgroundColor());
        float currentRotationX = startPropertyBean.getRotationX() + (endPropertyBean.getRotationX() - startPropertyBean.getRotationX()) * fraction;
        float currentSize = startPropertyBean.getSize() + (endPropertyBean.getSize() - startPropertyBean.getSize()) * fraction;

        return new PropertyBean(currentColor, currentRotationX, currentSize);
    }
}
