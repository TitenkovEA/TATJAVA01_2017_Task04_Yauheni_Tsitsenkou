package com.epam.catalog.dao.util.connectionpool.exception;

/**
 * Created by Yauheni_Tsitsenkou on 2/13/2017.
 */
public class ConnectionPoolException extends Exception {
    public ConnectionPoolException() {

    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Exception e) {
        super(e);
    }

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
