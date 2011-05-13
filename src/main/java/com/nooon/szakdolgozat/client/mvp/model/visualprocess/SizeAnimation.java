package com.nooon.szakdolgozat.client.mvp.model.visualprocess;


import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.nooon.szakdolgozat.client.mvp.model.sitecallback.SiteCallback;

/**
 * @author racka
 */
public class SizeAnimation extends SiteAnimation {

    private int startWidth;
    private int startHeight;
    private int targetWidth;
    private int targetHeight;

    public SizeAnimation(AbsolutePanel container, Widget target, SiteCallback callback, int duration,
                         int targetWidth, int targetHeight) {
        super(container, target, callback, duration);
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;

        startWidth = target.getOffsetWidth();
        startHeight = target.getOffsetHeight();
    }

    @Override
    public void animationLogic(double progress) {
        double fineTune = this.interpolate(progress);

        target.setWidth(startWidth + (int)(fineTune * (targetWidth - startWidth)) + "px");
        target.setHeight(startHeight + (int)(fineTune * (targetHeight - startHeight)) + "px");
    }
}
