package com.epam.catalog.view;

import com.epam.catalog.controller.Controller;

import java.util.Map;

/**
 * Created by Evgeny on 06.02.2017.
 */
public class View {
    private final Controller controller = new Controller();

    public void sendRequest(Map<String, String> request) {
        String response = controller.executeCommand(request);
        System.out.println(response);
    }

    public void startSession() {
        controller.initResource();
    }

    public void endSession() {
        controller.clearResource();
    }
}
