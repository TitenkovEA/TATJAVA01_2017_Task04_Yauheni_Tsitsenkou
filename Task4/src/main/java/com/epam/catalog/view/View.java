package com.epam.catalog.view;

import com.epam.catalog.controller.Controller;

import java.util.Map;

/**
 * Created by Evgeny on 06.02.2017.
 */
public class View {
    public void sendRequest(Map<String, String> request) {
        Controller controller = new Controller();
        String response = controller.executeCommand(request);
        System.out.println(response);
    }
}
