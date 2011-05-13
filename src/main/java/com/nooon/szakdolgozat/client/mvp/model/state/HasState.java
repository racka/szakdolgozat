package com.nooon.szakdolgozat.client.mvp.model.state;

public interface HasState {

    WebComponentState getState();
    void setState(WebComponentState newState);

}
