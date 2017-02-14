package com.epam.catalog.controller.command.impl;

import com.epam.catalog.controller.command.Command;

import java.util.Map;

/**
 * Created by Evgeny on 05.02.2017.
 */
public class WrongRequest implements Command {
    private static final String WRONG_COMMAND_MESSAGE = "Wrong command name.";

    public String execute(Map<String, String> request) {
       return WRONG_COMMAND_MESSAGE;
    }
}
