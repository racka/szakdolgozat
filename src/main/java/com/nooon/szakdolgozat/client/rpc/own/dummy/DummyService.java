package com.nooon.szakdolgozat.client.rpc.own.dummy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.XsrfProtect;

@XsrfProtect
@RemoteServiceRelativePath("dummyService")
public interface DummyService extends RemoteService {

    String dummyFunction(String paramJson);

}
