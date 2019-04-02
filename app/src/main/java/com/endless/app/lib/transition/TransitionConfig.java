package com.endless.app.lib.transition;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.Visibility;
import android.view.animation.Interpolator;

/**
 * 提供一个{@link Transition}
 * @author haosiyuan
 * @date 2019/4/2 4:40 PM
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class TransitionConfig {

    private Transition transition;

    private TransitionConfig(Transition transition) {
        this.transition = transition;
    }

    public static class Builder {

        /**
         * 动画类型
         */
        private Visibility visibility;
        /**
         * 持续时间
         */
        private long mDuration;
        /**
         * 开始时间延迟
         */
        private long mStartDelay;
        /**
         * 模式
         */
        private int mode = -1;
        /**
         * 插值器
         */
        private Interpolator mInterpolator;

        public Builder() {}

        public Builder newFade() {
            visibility = new Fade();
            return this;
        }

        public Builder newSlide(int slideEdge) {
            visibility = new Slide(slideEdge);
            return this;
        }

        public Builder newExplode() {
            visibility = new Explode();
            return this;
        }

        public Builder setDuration(long mDuration) {
            this.mDuration = mDuration;
            return this;
        }

        public Builder setStartDelay(long mStartDelay) {
            this.mStartDelay = mStartDelay;
            return this;
        }

        public Builder setMode(int mode) {
            this.mode = mode;
            return this;
        }

        public Builder setInterpolator(Interpolator mInterpolator) {
            this.mInterpolator = mInterpolator;
            return this;
        }

        public TransitionConfig build() {
            if (visibility == null) {
                throw new NullPointerException("Visibility is not build");
            }

            visibility.setDuration(mDuration);
            visibility.setStartDelay(mStartDelay);
            if (mode > 0) {
                visibility.setMode(mode);
            }

            if (mInterpolator != null) {
                visibility.setInterpolator(mInterpolator);
            }
            return new TransitionConfig(visibility);
        }
    }

    public Transition getTransition() {
        return transition;
    }
}
