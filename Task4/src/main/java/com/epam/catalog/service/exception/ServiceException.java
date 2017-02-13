package com.epam.catalog.service.exception;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class ServiceException extends Exception {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
