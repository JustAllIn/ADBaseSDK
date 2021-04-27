package com.adbase.sdk;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * 监听app前后台切换
 */
 abstract class ActivityLifeCycle implements Application.ActivityLifecycleCallbacks {

    /**
     * activity计数
     */
    private int activityCount;

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (activityCount == 0) { //后台切换到前台
            X.log.i("onAppForeground -> activity count = " + activityCount);
            onAppForeground();
        }
        activityCount++;
        X.log.i("life cycle callback -> activity count = " + activityCount);
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        X.log.i("life cycle callback -> activity count = " + activityCount);
        activityCount--;
        if (activityCount == 0) { //前台切换到后台
            X.log.i("onAppBackground -> activity count = " + activityCount);
            onAppBackground();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    /**
     * app回到前台
     */
    abstract void onAppForeground();

    /**
     * app退到后台
     */
    abstract void onAppBackground();
}
