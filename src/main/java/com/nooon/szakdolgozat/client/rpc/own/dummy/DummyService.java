package com.nooon.szakdolgozat.client.rpc.own.dummy;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dummyService")
public interface DummyService extends RemoteService {

    JSONObject dummyFunction();

}
