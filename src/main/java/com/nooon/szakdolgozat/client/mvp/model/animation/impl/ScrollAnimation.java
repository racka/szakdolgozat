package com.nooon.szakdolgozat.client.mvp.model.animation.impl;


import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.nooon.szakdolgozat.client.mvp.model.animation.SiteAnimation;
import com.nooon.szakdolgozat.client.mvp.model.sitecallback.SiteCallback;

public class ScrollAnimation extends SiteAnimation {

    private int startX;
    private int startY;
    private int targetX;
    private int targetY;

    public ScrollAnimation(AbsolutePanel container, Widget target, SiteCallback callback, int duration,
                           int targetX, int targetY) {
        super(container, target, callback, duration);
        this.targetX = targetX;
        this.targetY = targetY;
        this.startX = container.getWidgetLeft(target);
        this.startY = container.getWidgetTop(target);



    }

    @Override
    public void animationLogic(double progress) {
        double fineTune = this.interpolate(progress);
        container.setWidgetPosition(target,
                startX +  (int)(fineTune * (targetX - startX)),
                startY +  (int)(fineTune * (targetY - startY)));

    }
}
