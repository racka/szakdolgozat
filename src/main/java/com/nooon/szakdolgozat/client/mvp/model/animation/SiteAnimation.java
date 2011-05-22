package com.nooon.szakdolgozat.client.mvp.model.animation;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.nooon.szakdolgozat.client.mvp.model.sitecallback.SiteCallback;
import com.nooon.szakdolgozat.client.mvp.model.state.HasState;
import com.nooon.szakdolgozat.client.mvp.model.state.WebComponentState;


public abstract class SiteAnimation extends Animation implements HasState {

    protected AbsolutePanel container;
    protected Widget target;
    protected SiteCallback callback;
    protected int duration;
    protected WebComponentState state = WebComponentState.AVAILABLE;

    public SiteAnimation(AbsolutePanel container, Widget target, SiteCallback callback, int duration) {
        this.container = container;
        this.target = target;
        this.callback = callback;
        this.duration = duration;
    }

    protected SiteAnimation(Widget target, SiteCallback callback, int duration) {
        this.target = target;
        this.callback = callback;
        this.duration = duration;
    }

    protected SiteAnimation(SiteCallback callback, int duration) {
        this.callback = callback;
        this.duration = duration;
    }

    public void setCallback(SiteCallback callback) {
        this.callback = callback;
    }

    public abstract void animationLogic(double progress);

    public void animate() {
        if (WebComponentState.AVAILABLE.equals(state)) {
            this.run(duration);
        }
    }

    /**
     * @see Animation
     */
    @Override
    protected void onStart() {
        super.onStart();
        setState(WebComponentState.BUSY);
    }

    /**
     * @see Animation
     * 
     * @param progress
     */
    @Override
    protected void onUpdate(double progress) {
        animationLogic(progress);
    }

    /**
     * @see Animation
     */
    @Override
    protected void onComplete() {
        super.onComplete();
        if (callback != null) {
            callback.callback();
        }
        setState(WebComponentState.AVAILABLE);
    }

    /**
     * @see HasState
     *
     * @return
     */
    public WebComponentState getState() {
        return this.state;
    }

    /**
     * @see HasState
     * 
     * @param newState
     */
    public void setState(WebComponentState newState) {
        this.state = newState;
    }



}
