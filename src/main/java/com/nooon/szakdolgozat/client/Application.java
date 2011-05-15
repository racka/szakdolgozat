package com.nooon.szakdolgozat.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.RootPanel;

public class Application implements EntryPoint {

    public void onModuleLoad() {

        // XSRF token-hez workaround:
        if (!Cookies.getCookieNames().contains("JSESSIONID")) {
            Cookies.setCookie("JSESSIONID", "JSESSIONID", null, null, "/", false);
        }

        HistoryManager presenterManager = new HistoryManager();
        presenterManager.go(RootPanel.get());
        
    }
}
