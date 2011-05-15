package com.nooon.szakdolgozat.client.rpc.local.dummy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DummyServiceAsync {

    void dummyFunction(String paramJson, AsyncCallback<String> callback);

}
