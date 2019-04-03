package com.endless.app.propertyanim;

import android.view.animation.Interpolator;

/**
 * 自定义插值器
 */
public class SpeedUpInterpolator implements Interpolator {
    private final float mFactor;
    private final double mDoubleFactor;

    public SpeedUpInterpolator() {
        mFactor = 1.0f;
        mDoubleFactor = 2.0;
    }

    @Override
    public float getInterpolation(float v) {
        //实现核心代码块
        if (mFactor == 1.0f) {
            return v * v;
        } else {
            return (float) Math.pow(v, mDoubleFactor);
        }
    }
}
