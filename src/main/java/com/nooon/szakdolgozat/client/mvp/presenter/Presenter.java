/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nooon.szakdolgozat.client.mvp.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 *
 * @author racka
 */
public interface Presenter {

    void go(final HasWidgets container);
    void setup();
}
