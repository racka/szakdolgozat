/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nooon.szakdolgozat.client.mvp.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.nooon.szakdolgozat.client.mvp.view.View;

/**
 *
 * @author racka
 */
public class PresenterImpl implements Presenter {

    private final View display;

    public PresenterImpl(View display) {
        this.display = display;
        setup();
    }

    public void go(HasWidgets container) {
        container.add(display.asWidget());
    }

    public final void setup() {
    }

}
