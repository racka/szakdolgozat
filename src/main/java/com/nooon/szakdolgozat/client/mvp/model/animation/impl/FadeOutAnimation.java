package com.nooon.szakdolgozat.client.mvp.model.animation.impl;


import com.google.gwt.user.client.ui.Widget;
import com.nooon.szakdolgozat.client.mvp.model.animation.SiteAnimation;
import com.nooon.szakdolgozat.client.mvp.model.sitecallback.SiteCallback;

public class FadeOutAnimation extends SiteAnimation {

    private double firstThreshold;

    public FadeOutAnimation(Widget target, SiteCallback callback, int duration) {
        super(target, callback, duration);
        this.firstThreshold = 0;
    }

    public FadeOutAnimation(Widget target, double firstThreshold, SiteCallback callback, int duration) {
        super(target, callback, duration);
        this.firstThreshold = firstThreshold;
    }

    @Override
    public void animationLogic(double progress) {
        double fineTune = this.interpolate(progress);

        if (1 - fineTune >= firstThreshold) {
            target.getElement().getStyle().setOpacity(1 - fineTune);
        }
    }
}
