package com.nooon.szakdolgozat.client.rpc.own.dummy;

import com.google.gwt.core.client.JavaScriptObject;

public class DummyJSO extends JavaScriptObject {

    protected DummyJSO() {
    }

    public final native String getAttr() /*-{
        return this.attr;
    }-*/;

    public final native void setAttr(String value) /*-{
        this.attr = value;
    }-*/;


    public static final native DummyJSO buildFromJSON(String json) /*-{
        return eval('(' + json + ')');
    }-*/;

}
