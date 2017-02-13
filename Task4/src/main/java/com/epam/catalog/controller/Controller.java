package com.epam.catalog.controller;

import com.epam.catalog.controller.command.Command;

import java.util.Map;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class Controller {
    private static final CommandProvider provider = new CommandProvider();
    private static final String COMMAND_NAME = "COMMAND_NAME";

    public String executeCommand(Map<String, String> request) {
        String commandName = request.get(COMMAND_NAME);

        Command command = provider.getCommand(commandName);

        return command.execute(request);
    }
}
