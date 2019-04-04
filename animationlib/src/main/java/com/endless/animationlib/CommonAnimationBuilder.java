package com.endless.animationlib;

import android.os.Build;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * @author haosiyuan
 * @date 2019/4/4 3:21 PM
 */
public class CommonAnimationBuilder {

    private Animation animation;

    public CommonAnimationBuilder(Animation animation) {
        this.animation = animation;
    }

    public static class Builder {

        private Animation animation;

        public Builder duration(long mDuration) {
            animation.setDuration(mDuration);
            return this;
        }

        public Builder repeatCount(int repeatCount) {
            animation.setRepeatCount(repeatCount);
            return this;
        }

        public Builder repeatMode(int mode) {
            animation.setRepeatMode(mode);
            return this;
        }

        public Builder isStay(boolean isStay) {
            animation.setFillAfter(isStay);
            animation.setFillBefore(!isStay);
            return this;
        }

        public CommonAnimationBuilder build() {
            return new CommonAnimationBuilder(animation);
        }
    }

    public Animation getAnimation() {
        return animation;
    }
}
