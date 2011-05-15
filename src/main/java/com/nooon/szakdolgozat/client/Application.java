package com.nooon.szakdolgozat.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * A modul belepesi pontja
 */
public class Application implements EntryPoint {

    public void onModuleLoad() {

        // XSRF token-hez workaround:
        if (!Cookies.getCookieNames().contains("JSESSIONID")) {
            String initialValue = "JSESSIONID";
            Cookies.setCookie("JSESSIONID", initialValue, null, null, "/", false);
        }

        HistoryManager historyManager = new HistoryManager();
        historyManager.go(RootPanel.get());
        
    }
}
