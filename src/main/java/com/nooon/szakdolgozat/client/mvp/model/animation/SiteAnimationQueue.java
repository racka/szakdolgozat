package com.nooon.szakdolgozat.client.mvp.model.animation;

import com.nooon.szakdolgozat.client.mvp.model.sitecallback.SiteCallback;
import com.nooon.szakdolgozat.client.mvp.model.state.HasState;
import com.nooon.szakdolgozat.client.mvp.model.state.WebComponentState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class SiteAnimationQueue implements HasState {

    private List<SiteAnimation> queue = new ArrayList<SiteAnimation>();
    private WebComponentState state;


    public SiteAnimationQueue(List<SiteAnimation> siteAnimations1) {
        init(siteAnimations1);
    }

    /**
     *
     * @param siteAnimations1 az animaciok listaja, amit egymas utan akarunk lejatszani
     */
    public SiteAnimationQueue(SiteAnimation... siteAnimations1) {
        init(Arrays.asList(siteAnimations1));
    }

    private void init(List<SiteAnimation> siteAnimations) {
        if (siteAnimations != null && siteAnimations.size() > 0) {
            queue.addAll(siteAnimations);

            // az utolso animacio allitja vissza aktivva az allapotot
            ListIterator<SiteAnimation> listIterator = queue.listIterator(queue.size());
            SiteAnimation tmpNextAnimation = listIterator.previous();
            final SiteAnimationQueue thisQueue = this;
            tmpNextAnimation.setCallback(new SiteCallback() {

                public void callback() {
                    thisQueue.setState(WebComponentState.AVAILABLE);
                }
            });

            while (listIterator.hasPrevious()) {
                final SiteAnimation nextAnimation = tmpNextAnimation;
                SiteAnimation prevAnimation = listIterator.previous();
                prevAnimation.setCallback(new SiteCallback() {

                    public void callback() {
                        nextAnimation.animate();
                    }
                });
                tmpNextAnimation = prevAnimation;
            }

        }

        state = WebComponentState.AVAILABLE;

    }


    public void start() {
        if (WebComponentState.AVAILABLE.equals(state) && !queue.isEmpty()) {
            state = WebComponentState.BUSY;
            queue.get(0).animate();
        }
    }

    /**
     * @see HasState
     *
     * @return
     */
    public WebComponentState getState() {
        return state;
    }

    /**
     * @see HasState
     * @param state
     */
    public void setState(WebComponentState state) {
        this.state = state;
    }

    /**
     *
     * @param queueCallback a teljes queue lefutasa utan ervenyes logika
     * FONTOS: {@link HasState} -nel itt allitsuk vissza az animaciohoz
     * tartozo szulo allapotat!
     */
    public void setCallback(final SiteCallback queueCallback) {
        if (queue.size() > 0) {
            ListIterator<SiteAnimation> listIterator = queue.listIterator(queue.size() - 1);
            SiteAnimation tmpNextAnimation = listIterator.next();
            final SiteAnimationQueue thisQueue = this;
            tmpNextAnimation.setCallback(new SiteCallback() {

                public void callback() {
                    thisQueue.setState(WebComponentState.AVAILABLE);
                    queueCallback.callback();
                }
            });

        }
    }

}
