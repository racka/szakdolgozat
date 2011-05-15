package com.nooon.szakdolgozat.server.rpc.own.dummy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import com.nooon.szakdolgozat.client.rpc.own.dummy.DummyService;
import com.nooon.szakdolgozat.client.shared.overlay.IDummyServerAutoBeanFactory;
import com.nooon.szakdolgozat.client.shared.overlay.IDummyServerRequest;
import com.nooon.szakdolgozat.client.shared.overlay.IDummyServerResponse;

public class DummyServiceImpl extends RemoteServiceServlet implements DummyService {

    public String dummyFunction(String paramJson) {

        IDummyServerAutoBeanFactory abFactory = AutoBeanFactorySource.create(IDummyServerAutoBeanFactory.class);

        // a kapott parameter deserializalasa
        AutoBean<IDummyServerRequest> bean = AutoBeanCodex.decode(abFactory, IDummyServerRequest.class, paramJson);
        IDummyServerRequest request = bean.as();

        // a valasz osszeallitasa
        AutoBean<IDummyServerResponse> requestAutoBean = abFactory.response();
        IDummyServerResponse response = requestAutoBean.as();
        response.setResponse("Hello " + request.getRequestParameter() + ", this is personal data!");

        // a valasz serializalasa
        AutoBean<IDummyServerResponse> controller = AutoBeanUtils.getAutoBean(response);
        return AutoBeanCodex.encode(controller).getPayload();

    }
}
