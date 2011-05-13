/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nooon.szakdolgozat.client.mvp.view;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.nooon.szakdolgozat.client.mvp.view.home.Home;
import com.nooon.szakdolgozat.client.resource.SzakdolgozatStyleSheet;
import com.nooon.szakdolgozat.client.resource.bundle.SzakdolgozatClientBundle;


/**
 * A konkret widget-ek megvalositasa
 *
 * @author racka
 */
public class ViewImpl extends Composite implements View {

    private SzakdolgozatStyleSheet CSS = SzakdolgozatClientBundle.INSTANCE.constantsCSS();

    public ViewImpl() {

        initWidget(new Home());

        Document.get().getBody().getStyle().setBackgroundColor(CSS.bodyBackgroundColor());
    }


    @Override
    public Widget asWidget() {
        return this;
    }
}
