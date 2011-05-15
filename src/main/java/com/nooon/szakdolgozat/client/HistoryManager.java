package com.nooon.szakdolgozat.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.nooon.szakdolgozat.client.mvp.presenter.Presenter;
import com.nooon.szakdolgozat.client.mvp.presenter.PresenterImpl;
import com.nooon.szakdolgozat.client.mvp.view.ViewImpl;

public class HistoryManager implements Presenter, ValueChangeHandler<String> {

    private static final String MAIN_PAGE_ID = "mainPage";
    private HasWidgets container;

    public HistoryManager() {
        setup();
    }

    public final void setup() {
        History.addValueChangeHandler(this);
    }

    public void go(HasWidgets container) {
        this.container = container;
        
        if ("".equals(History.getToken())) {
            History.newItem(MAIN_PAGE_ID);
        }

    }

    public void onValueChange(ValueChangeEvent<String> event) {
        String target = event.getValue();

        if (target != null) {
            Presenter presenter = null;

            if (target.equals(MAIN_PAGE_ID)) {
                presenter = new PresenterImpl(new ViewImpl());
            }

            if (presenter != null) {
                presenter.go(container);
            }

        }
    }
}
