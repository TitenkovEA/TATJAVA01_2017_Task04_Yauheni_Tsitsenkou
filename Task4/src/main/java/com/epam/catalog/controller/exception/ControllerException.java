package com.epam.catalog.controller.exception;

/**
 * Created by Yauheni_Tsitsenkou on 2/17/2017.
 */
public class ControllerException extends RuntimeException {
    public ControllerException() {
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(Exception e) {
        super(e);
    }

    public ControllerException(String message, Exception e) {
        super(message, e);
    }
}
