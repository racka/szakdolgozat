package com.nooon.szakdolgozat.client.shared.overlay;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface IDummyServerAutoBeanFactory extends AutoBeanFactory {

    AutoBean<IDummyServerRequest> request();
    AutoBean<IDummyServerResponse> response();

}
