package com.endless.app.lib.transition;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

/**
 * AOP完成注解 转场动画的设置
 * @author haosiyuan
 * @date 2019/4/2 4:41 PM
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ActivityTransition {

    private TransitionConfig config;

    public void setExitTransition(Activity activity) {
        activity.getWindow().setExitTransition(config.getTransition());
    }

    public void setReenterTransition(Activity activity) {
        activity.getWindow().setReenterTransition(config.getTransition());
    }

    public void setEnterTransition(Activity activity) {
        activity.getWindow().setEnterTransition(config.getTransition());
    }

    public void setReturnTransition(Activity activity) {
        activity.getWindow().setReturnTransition(config.getTransition());
    }
}
