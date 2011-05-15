package com.nooon.szakdolgozat.client.mvp.model.animation.impl;


import com.google.gwt.user.client.ui.Image;
import com.nooon.szakdolgozat.client.mvp.model.animation.SiteAnimation;
import com.nooon.szakdolgozat.client.mvp.model.sitecallback.SiteCallback;

public class ImageResizeAnimation extends SiteAnimation {

    private int startWidth;
    private int startHeight;
    private int targetWidth;
    private int targetHeight;

    public ImageResizeAnimation(Image target, SiteCallback callback, int duration,
                                int targetWidth, int targetHeight) {
        super(null, target, callback, duration);
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;

        startWidth = target.getWidth();
        startHeight = target.getHeight();
    }

    @Override
    public void animationLogic(double progress) {
        double fineTune = this.interpolate(progress);

        target.setPixelSize(startWidth + (int)(fineTune * (targetWidth - startWidth)),
                startHeight + (int)(fineTune * (targetHeight - startHeight)));
    }
}
