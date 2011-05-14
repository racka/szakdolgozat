package com.nooon.szakdolgozat.client.rpc.own.dummy;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

public interface DummyServiceAsync {

    void dummyFunction(AsyncCallback<JSONObject> callback);

}
