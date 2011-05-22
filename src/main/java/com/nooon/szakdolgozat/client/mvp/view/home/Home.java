package com.nooon.szakdolgozat.client.mvp.view.home;

import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickEvent;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickHandler;
import com.bradrydzewski.gwt.calendar.client.event.UpdateEvent;
import com.bradrydzewski.gwt.calendar.client.event.UpdateHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.*;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.nooon.szakdolgozat.client.mvp.model.animation.impl.FadeInAnimation;
import com.nooon.szakdolgozat.client.mvp.model.animation.impl.FadeOutAnimation;
import com.nooon.szakdolgozat.client.mvp.model.sitecallback.SiteCallback;
import com.nooon.szakdolgozat.client.mvp.view.home.calendar.AddAppointment;
import com.nooon.szakdolgozat.client.resource.SzakdolgozatStyleSheet;
import com.nooon.szakdolgozat.client.resource.bundle.SzakdolgozatClientBundle;
import com.nooon.szakdolgozat.client.rpc.local.dummy.DummyService;
import com.nooon.szakdolgozat.client.rpc.local.dummy.DummyServiceAsync;
import com.nooon.szakdolgozat.client.shared.autobean.dummyservice.IDummyServiceAutoBeanFactory;
import com.nooon.szakdolgozat.client.shared.autobean.dummyservice.IDummyServiceRequest;
import com.nooon.szakdolgozat.client.shared.autobean.dummyservice.IDummyServiceResponse;

import java.util.Date;


public class Home extends Composite {

    /**
     * a deklarativ elrendezeshez a tipus
     */
    interface HomeUIBinder extends UiBinder<Widget, Home> {
    }

    /**
     * a deklarativ elrendezes peldanya (GWT - UIBinder)
     */
    private static HomeUIBinder uiBinder = GWT.create(HomeUIBinder.class);

    /**
     * faluleti stilus konstansokat tartalmazo inline css
     */
    private final SzakdolgozatStyleSheet CSS = SzakdolgozatClientBundle.INSTANCE.constantsCSS();

    /**
     * az uzleti logikat tartalmazo service
     */
    private final DummyServiceAsync dummyService = GWT.create(DummyService.class);

    /**
     * a service parametereit elkeszito factory
     */
    private final IDummyServiceAutoBeanFactory dummyServiceAutoBeanFactory = GWT.create(IDummyServiceAutoBeanFactory.class);


    @UiField
    public AbsolutePanel mainAbsolutePanel;
    @UiField
    public HTMLPanel transparentDiv;

    public Home() {
        initWidget(uiBinder.createAndBindUi(this));

        mainAbsolutePanel.setSize(CSS.maxWidth(), CSS.maxHeight());
        transparentDiv.getElement().getStyle().setZIndex(-1);
        transparentDiv.setVisible(false);

        createLoginPanel();

        createCalendar();

    }

    @UiField
    public HTMLPanel loginContainer;
    @UiField
    public TextBox userNameInput;
    @UiField
    public Button loginButton;

    /**
     *
     */
    private void createLoginPanel() {
        loginContainer.getElement().getStyle().setZIndex(1);
        loginContainer.setVisible(true);
        mainAbsolutePanel.setWidgetPosition(loginContainer
                , Window.getClientWidth() / 2 - 150
                , Window.getClientHeight() / 2);
    }


    private Calendar calendar;
    @UiField
    public HTMLPanel calendarContainer;
    @UiField
    public Label calendarHeader;
    @UiField
    public AddAppointment addAppointment;

    /**
     *
     */
    private void createCalendar() {
        calendarContainer.setSize(CSS.calendarWidth(), CSS.calendarHeight());
        calendarContainer.setVisible(false);

        calendar = new Calendar();
        calendar.setDate(new Date());
        calendar.setDays(7);
        calendar.setSize(CSS.calendarWidth(), CSS.calendarHeight());

        calendar.addTimeBlockClickHandler(new TimeBlockClickHandler<Date>() {
            public void onTimeBlockClick(TimeBlockClickEvent<Date> event) {
                timeBlockClickAction(event);
            }
        });

        calendar.addUpdateHandler(new UpdateHandler<Appointment>() {
            public void onUpdate(UpdateEvent<Appointment> event) {
                boolean commit = Window.confirm(
                        "Are you sure you want to update the appointment \""
                                + event.getTarget().getTitle() + "\"");
                if (!commit) {
                    event.setCancelled(true); //Cancel Appointment update
                }
            }
        });

        calendarContainer.add(calendar);
        mainAbsolutePanel.setWidgetPosition(calendarContainer
                , Window.getClientWidth() / 2 - 512
                , Window.getClientHeight() / 2 - 250);

        addAppointment.setCalendar(calendar);
        addAppointment.setCallback(new SiteCallback() {
            public void callback() {
                new FadeOutAnimation(addAppointment, new SiteCallback() {
                    public void callback() {
                        transparentDiv.setVisible(false);
                        addAppointment.setVisible(false);
                    }
                }, 1000).animate();
            }
        });
        addAppointment.setVisible(false);
    }

    /**
     *
     * @param event
     */
    private void timeBlockClickAction(TimeBlockClickEvent<Date> event) {
        mainAbsolutePanel.setWidgetPosition(addAppointment,
                (Window.getClientWidth() / 2) - 200,
                (Window.getClientHeight() / 2) - 100);

        transparentDiv.setVisible(true);
        transparentDiv.getElement().getStyle().setZIndex(10);
        new FadeInAnimation(transparentDiv, 0.3, null, 500).animate();

        addAppointment.setVisible(true);
        addAppointment.getElement().getStyle().setZIndex(11);
        addAppointment.initEvent(event);
        new FadeInAnimation(addAppointment, null, 500).animate();

    }


    /**
     * @param e
     */
    @UiHandler("loginButton")
    void handleLogin(ClickEvent e) {

        if (userNameInput.getText() != null && userNameInput.getText().length() > 0) {
            String safeString = "";
            String[] tagSplitted = userNameInput.getText().split("<[^>]*>");
            if (tagSplitted.length > 0) {
                for (String nativeString : tagSplitted) {
                    safeString = safeString + nativeString;
                }
            }
            domainLogic(safeString);
        } else {
            Window.alert("Kérem adjon meg egy azonosítót!");
        }
    }


    /**
     * @param identifier
     */
    private void domainLogic(final String identifier) {

        // XSRF token elkeszitese
        XsrfTokenServiceAsync xsrf = (XsrfTokenServiceAsync) GWT.create(XsrfTokenService.class);
        ((ServiceDefTarget) xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");

        // token igenyles
        xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {

            public void onSuccess(XsrfToken token) {
                ((HasRpcToken) dummyService).setRpcToken(token);
                dummyServiceInvocation(identifier);
            }

            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

        });

    }

    /**
     * @param identifier
     */
    private void dummyServiceInvocation(String identifier) {
        // rpc parameter elkeszitese
        AutoBean<IDummyServiceRequest> requestAutoBean = dummyServiceAutoBeanFactory.request();
        IDummyServiceRequest request = requestAutoBean.as();
        request.setRequestParameter(identifier);

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

                        AutoBean<IDummyServiceResponse> responseAutoBean = AutoBeanCodex.decode(
                                dummyServiceAutoBeanFactory, IDummyServiceResponse.class, jsonObject);
                        final IDummyServiceResponse response = responseAutoBean.as();

                        new FadeOutAnimation(loginContainer, new SiteCallback() {
                            public void callback() {
                                loginContainer.setVisible(false);
                                calendarContainer.setVisible(true);
                                // a szerver oldalrol nem kell tartani tamadasra
                                calendarHeader.setText("A szerver válasza: \"" + response.getResponse() + "\"");
                            }
                        }, 1000).animate();
                    }
                });

    }

}
