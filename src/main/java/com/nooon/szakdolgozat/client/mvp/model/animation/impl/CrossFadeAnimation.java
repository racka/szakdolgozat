package com.nooon.szakdolgozat.client.mvp.model.animation.impl;


import com.google.gwt.user.client.ui.Widget;
import com.nooon.szakdolgozat.client.mvp.model.animation.SiteAnimation;
import com.nooon.szakdolgozat.client.mvp.model.sitecallback.SiteCallback;

public class CrossFadeAnimation extends SiteAnimation {

    private Widget first;
    private Widget second;

    public CrossFadeAnimation(Widget first, Widget second, SiteCallback callback, int duration) {
        super(null, null, callback, duration);
        this.first = first;
        this.second = second;
    }

    @Override
    public void animationLogic(double progress) {
        double fineTune = this.interpolate(progress);

        if (first != null) {
            first.getElement().getStyle().setOpacity(1 - fineTune);
        }
        second.getElement().getStyle().setOpacity(fineTune);
    }
}
