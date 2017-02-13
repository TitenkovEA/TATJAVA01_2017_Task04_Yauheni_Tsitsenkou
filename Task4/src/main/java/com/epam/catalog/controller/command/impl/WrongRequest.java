package com.epam.catalog.controller.command.impl;

import com.epam.catalog.controller.command.Command;

import java.util.Map;

/**
 * Created by Evgeny on 05.02.2017.
 */
public class WrongRequest implements Command {
    public String execute(Map<String, String> request) {
       return "Wrong command name.";
    }
}
