package com.nooon.szakdolgozat.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Application implements EntryPoint {

    public void onModuleLoad() {

        HistoryManager presenterManager = new HistoryManager();
        presenterManager.go(RootPanel.get());
        
    }
}
