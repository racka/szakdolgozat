package com.nooon.szakdolgozat.client.resource.bundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.nooon.szakdolgozat.client.resource.SzakdolgozatStyleSheet;

public interface SzakdolgozatClientBundle extends ClientBundle{

    public static final SzakdolgozatClientBundle INSTANCE =  GWT.create(SzakdolgozatClientBundle.class);

    @Source("com/nooon/szakdolgozat/public/stylesheets/constants.css")
    SzakdolgozatStyleSheet constantsCSS();


}
