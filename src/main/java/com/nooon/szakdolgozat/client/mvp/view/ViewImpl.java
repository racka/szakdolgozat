package com.nooon.szakdolgozat.client.mvp.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.nooon.szakdolgozat.client.mvp.view.home.Home;

public class ViewImpl extends Composite implements View {

    public ViewImpl() {

        initWidget(new Home());
    }


    @Override
    public Widget asWidget() {
        return this;
    }
}
