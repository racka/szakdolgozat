package com.nooon.szakdolgozat.server.rpc.local.dummy;

import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import com.nooon.szakdolgozat.client.rpc.local.dummy.DummyService;
import com.nooon.szakdolgozat.client.shared.autobean.dummyservice.IDummyServiceAutoBeanFactory;
import com.nooon.szakdolgozat.client.shared.autobean.dummyservice.IDummyServiceRequest;
import com.nooon.szakdolgozat.client.shared.autobean.dummyservice.IDummyServiceResponse;

public class DummyServiceImpl extends XsrfProtectedServiceServlet implements DummyService {

    public String dummyFunction(String paramJson) {

        IDummyServiceAutoBeanFactory abFactory = AutoBeanFactorySource.create(IDummyServiceAutoBeanFactory.class);

        // a kapott parameter deserializalasa
        AutoBean<IDummyServiceRequest> bean = AutoBeanCodex.decode(abFactory, IDummyServiceRequest.class, paramJson);
        IDummyServiceRequest request = bean.as();

        // a valasz osszeallitasa
        AutoBean<IDummyServiceResponse> requestAutoBean = abFactory.response();
        IDummyServiceResponse response = requestAutoBean.as();
        response.setResponse(request.getRequestParameter() + " XSRF védelem alatt.");

        // a valasz serializalasa
        AutoBean<IDummyServiceResponse> controller = AutoBeanUtils.getAutoBean(response);
        return AutoBeanCodex.encode(controller).getPayload();

    }
}
