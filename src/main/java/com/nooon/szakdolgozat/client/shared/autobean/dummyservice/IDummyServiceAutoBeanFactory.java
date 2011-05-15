package com.nooon.szakdolgozat.client.shared.autobean.dummyservice;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface IDummyServiceAutoBeanFactory extends AutoBeanFactory {

    AutoBean<IDummyServiceRequest> request();
    AutoBean<IDummyServiceResponse> response();

}
