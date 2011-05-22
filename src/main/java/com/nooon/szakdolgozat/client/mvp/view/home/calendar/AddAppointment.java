package com.nooon.szakdolgozat.client.mvp.view.home.calendar;

import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.AppointmentStyle;
import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.nooon.szakdolgozat.client.mvp.model.sitecallback.SiteCallback;
import com.nooon.szakdolgozat.client.resource.SzakdolgozatStyleSheet;
import com.nooon.szakdolgozat.client.resource.bundle.SzakdolgozatClientBundle;

import java.util.Date;

public class AddAppointment extends Composite {

    private final SzakdolgozatStyleSheet CSS = SzakdolgozatClientBundle.INSTANCE.constantsCSS();

    interface AddAppointmentUiBinder extends UiBinder<Widget, AddAppointment> {
    }

    private static AddAppointmentUiBinder uiBinder = GWT.create(AddAppointmentUiBinder.class);

    @UiField
    public VerticalPanel verticalPanel;
    @UiField
    public Label modalLabel;

    public AddAppointment() {

        initWidget(uiBinder.createAndBindUi(this));
        verticalPanel.setSize(CSS.addAppVerticalWidth(), CSS.addAppVerticalHeight());
        verticalPanel.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
    }


    private Calendar calendar;

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }


    /** a komponens bezarasakor ervenyes logika */
    private SiteCallback callback;

    public void setCallback(SiteCallback callback) {
        this.callback = callback;
    }


    @UiField
    public TextBox title;
    @UiField
    public TextBox description;

    @UiField
    public Button addButton;

    @UiHandler("addButton")
    void handleAddAppointment(ClickEvent e) {
        if (calendar != null) {
            manageNewAppointment(event, title.getText(), description.getText());
        }
        callback.callback();
    }

    /**
     *
     * @param event
     * @param title
     * @param description
     */
    private void manageNewAppointment(TimeBlockClickEvent<Date> event, String title, String description) {
        Appointment appt = new Appointment();
        appt.setStart(event.getTarget());
        appt.setEnd(event.getTarget());
        appt.setTitle(title);
        appt.setDescription(description);
        appt.setStyle(AppointmentStyle.GREY);
        calendar.addAppointment(appt);
    }



    private TimeBlockClickEvent<Date> event;

    public void initEvent(TimeBlockClickEvent<Date> event) {
        this.event = event;
        this.modalLabel.setText("Új bejegyzés (" + event.getTarget() + ")");
    }
}
