package com.nooon.szakdolgozat.server.rpc.own.dummy;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nooon.szakdolgozat.client.rpc.own.dummy.DummyService;

public class DummyServiceImpl extends RemoteServiceServlet implements DummyService {

    public JSONObject dummyFunction() {
        return null;
    }
}
