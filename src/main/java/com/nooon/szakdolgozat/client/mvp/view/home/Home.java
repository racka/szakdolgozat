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
import com.nooon.szakdolgozat.client.resource.SzakdolgozatStyleSheet;
import com.nooon.szakdolgozat.client.resource.bundle.SzakdolgozatClientBundle;
import com.nooon.szakdolgozat.client.rpc.local.dummy.DummyService;
import com.nooon.szakdolgozat.client.rpc.local.dummy.DummyServiceAsync;
import com.nooon.szakdolgozat.client.shared.autobean.dummyservice.IDummyServiceAutoBeanFactory;
import com.nooon.szakdolgozat.client.shared.autobean.dummyservice.IDummyServiceRequest;
import com.nooon.szakdolgozat.client.shared.autobean.dummyservice.IDummyServiceResponse;


public class Home extends Composite {

    /** a deklarativ elrendezeshez a tipus */
    interface StartFrameUIBinder extends UiBinder<Widget, Home> {
    }

    /** a deklarativ elrendezes peldanya (GWT - UIBinder) */
    private static StartFrameUIBinder uiBinder = GWT.create(StartFrameUIBinder.class);

    /** faluleti stilus konstansokat tartalmazo inline css */
    private final SzakdolgozatStyleSheet CSS = SzakdolgozatClientBundle.INSTANCE.constantsCSS();

    /** az uzleti logikat tartalmazo service */
    private final DummyServiceAsync dummyService = GWT.create(DummyService.class);

    /** a service parametereit elkeszito factory */
    private final IDummyServiceAutoBeanFactory dummyServiceAutoBeanFactory = GWT.create(IDummyServiceAutoBeanFactory.class);

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

        // token igenyles
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
        AutoBean<IDummyServiceRequest> requestAutoBean = dummyServiceAutoBeanFactory.request();
        IDummyServiceRequest request = requestAutoBean.as();
        request.setRequestParameter("Valentino Rossi");

        // rpc parameter serializalasa
        AutoBean<IDummyServiceRequest> controller = AutoBeanUtils.getAutoBean(request);
        String jsonParameter = AutoBeanCodex.encode(controller).getPayload();

        // rpc hivas
        dummyService.dummyFunction(
                jsonParameter,
                new AsyncCallback<String>() {
                    public void onFailure(Throwable throwable) {
                        Window.alert(throwable.getMessage());
                    }

                    public void onSuccess(String jsonObject) {

                        AutoBean<IDummyServiceResponse> responseAutoBean = AutoBeanCodex.decode(dummyServiceAutoBeanFactory, IDummyServiceResponse.class, jsonObject);
                        IDummyServiceResponse response = responseAutoBean.as();

                        Window.alert(response.getResponse());
                    }
                });

    }

//  A feluleti logikahoz fuggvenyek :
//
//    /**
//     * vertikalis scroll
//     *
//     * @param target
//     * @param targetX
//     */
//    private void verticalClientScroll(Widget target, int targetX) {
//        moveClientTo(target, targetX, mainAbsolutePanel.getWidgetTop(target), 1000);
//
//    }
//
//    /**
//     * mainAbsolutePanel -en beluli kozvetlen kliens mozgatasa
//     *
//     * @param target
//     * @param targetX
//     * @param targetY
//     * @param duration
//     */
//    private void moveClientTo(Widget target, int targetX, int targetY, int duration) {
//        ScrollAnimation animation = new ScrollAnimation(
//                mainAbsolutePanel, target, null,
//                duration,
//                targetX, targetY);
//
//        animation.animate();
//    }

}
