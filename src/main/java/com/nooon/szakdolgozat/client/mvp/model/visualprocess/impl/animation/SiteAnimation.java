/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nooon.szakdolgozat.client.mvp.model.visualprocess.impl.animation;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.nooon.szakdolgozat.client.mvp.model.sitecallback.SiteCallback;
import com.nooon.szakdolgozat.client.mvp.model.state.HasState;
import com.nooon.szakdolgozat.client.mvp.model.state.WebComponentState;
import com.nooon.szakdolgozat.client.mvp.model.visualprocess.VisualProcess;

/**
 *
 * @author racka
 */
public abstract class SiteAnimation extends Animation implements VisualProcess, HasState {

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

    public void processLogic(double progress) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setProcessDuration() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTarget() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void start() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



    public void setCallback(SiteCallback callback) {
        this.callback = callback;
    }

    public void setContainer(AbsolutePanel container) {
        this.container = container;
    }

    public void setTarget(Widget target) {
        this.target = target;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
        setState(WebComponentState.BUSY);
        super.onStart();
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
        if (callback != null) {
            callback.callback();
        }
        setState(WebComponentState.AVAILABLE);
        super.onComplete();
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
