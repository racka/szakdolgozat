package com.nooon.szakdolgozat.client.mvp.view.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.*;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.nooon.szakdolgozat.client.mvp.model.visualprocess.ScrollAnimation;
import com.nooon.szakdolgozat.client.resource.SzakdolgozatStyleSheet;
import com.nooon.szakdolgozat.client.resource.bundle.SzakdolgozatClientBundle;
import com.nooon.szakdolgozat.client.rpc.own.dummy.DummyService;
import com.nooon.szakdolgozat.client.rpc.own.dummy.DummyServiceAsync;
import com.nooon.szakdolgozat.client.shared.overlay.IDummyServerAutoBeanFactory;
import com.nooon.szakdolgozat.client.shared.overlay.IDummyServerRequest;
import com.nooon.szakdolgozat.client.shared.overlay.IDummyServerResponse;


public class Home extends Composite {

    interface StartFrameUIBinder extends UiBinder<Widget, Home> {
    }

    private static StartFrameUIBinder uiBinder = GWT.create(StartFrameUIBinder.class);

    private final SzakdolgozatStyleSheet CSS = SzakdolgozatClientBundle.INSTANCE.constantsCSS();

    private final DummyServiceAsync dummyService = GWT.create(DummyService.class);


    @UiField
    public AbsolutePanel mainAbsolutePanel;

    public Home() {
        initWidget(uiBinder.createAndBindUi(this));

        mainAbsolutePanel.setSize(CSS.maxWidth(), CSS.maxHeight());

        domainLogic();
    }

    /**
     * Az uzleti logika
     */
    private void domainLogic() {

        // XSRF token elkeszitese
        XsrfTokenServiceAsync xsrf = (XsrfTokenServiceAsync) GWT.create(XsrfTokenService.class);
        ((ServiceDefTarget) xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");


        xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {

            public void onSuccess(XsrfToken token) {
                ((HasRpcToken) dummyService).setRpcToken(token);
                dummyServiceInvocation();
            }

            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

        });

    }

    /**
     * sajat service hivasa
     */
    private void dummyServiceInvocation() {
        // rpc parameter elkeszitese
        final IDummyServerAutoBeanFactory abFactory = GWT.create(IDummyServerAutoBeanFactory.class);
        AutoBean<IDummyServerRequest> requestAutoBean = abFactory.request();
        IDummyServerRequest request = requestAutoBean.as();
        request.setRequestParameter("Valentino Rossi");

        // rpc parameter serializalasa
        AutoBean<IDummyServerRequest> controller = AutoBeanUtils.getAutoBean(request);
        String jsonParameter = AutoBeanCodex.encode(controller).getPayload();

        // rpc hivas
        dummyService.dummyFunction(
                jsonParameter,
                new AsyncCallback<String>() {
                    public void onFailure(Throwable throwable) {
                        Window.alert(throwable.getMessage());
                    }

                    public void onSuccess(String jsonObject) {

                        AutoBean<IDummyServerResponse> responseAutoBean = AutoBeanCodex.decode(abFactory, IDummyServerResponse.class, jsonObject);
                        IDummyServerResponse response = responseAutoBean.as();

                        Window.alert(response.getResponse());
                    }
                });

    }

    /**
     * vertikalis scroll
     *
     * @param target
     * @param targetX
     */

    private void verticalClientScroll(Widget target, int targetX) {
        moveClientTo(target, targetX, mainAbsolutePanel.getWidgetTop(target), 1000);

    }

    /**
     * mainAbsolutePanel -en beluli kozvetlen kliens mozgatasa
     *
     * @param target
     * @param targetX
     * @param targetY
     * @param duration
     */
    private void moveClientTo(Widget target, int targetX, int targetY, int duration) {
        ScrollAnimation animation = new ScrollAnimation(
                mainAbsolutePanel, target, null,
                duration,
                targetX, targetY);

        animation.animate();
    }

}
