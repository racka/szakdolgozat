package com.nooon.szakdolgozat.client.mvp.view.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.nooon.szakdolgozat.client.mvp.model.visualprocess.ScrollAnimation;
import com.nooon.szakdolgozat.client.resource.SzakdolgozatStyleSheet;
import com.nooon.szakdolgozat.client.resource.bundle.SzakdolgozatClientBundle;


public class Home extends Composite {

    interface StartFrameUIBinder extends UiBinder<Widget, Home> {
    }

    private static StartFrameUIBinder uiBinder = GWT.create(StartFrameUIBinder.class);

    private SzakdolgozatStyleSheet CSS = SzakdolgozatClientBundle.INSTANCE.constantsCSS();


    @UiField
    public AbsolutePanel mainAbsolutePanel;

    public Home() {
        initWidget(uiBinder.createAndBindUi(this));

        mainAbsolutePanel.setSize(CSS.maxWidth(), CSS.maxHeight());

    }

    /**
     * vertikalis scroll
     *
     * @param target
     * @param targetX
     */
    private void verticalClientScroll(Widget target, int targetX) {
        moveClientTo(target, targetX, mainAbsolutePanel.getWidgetTop(target), 1000);

    }

    /**
     * mainAbsolutePanel -en beluli kozvetlen kliens mozgatasa
     *
     * @param target
     * @param targetX
     * @param targetY
     * @param duration
     */
    private void moveClientTo(Widget target, int targetX, int targetY, int duration) {
        ScrollAnimation animation = new ScrollAnimation(
                mainAbsolutePanel, target, null,
                duration,
                targetX, targetY);

        animation.animate();
    }

}
