package com.nooon.szakdolgozat.client.mvp.model.visualprocess;


import com.nooon.szakdolgozat.client.mvp.model.sitecallback.SiteCallback;

/**
 * @author racka
 */
public class StaticAnimation extends SiteAnimation {

    private SiteCallback staticCallback;
    public StaticAnimation(SiteCallback callback) {
        super(null, null, callback, 1);
        this.staticCallback = callback;
    }

    private boolean done = false;

    @Override
    public void animationLogic(double progress) {
        if (!done) {
            done = true;
            staticCallback.callback();
        }
    }
}
