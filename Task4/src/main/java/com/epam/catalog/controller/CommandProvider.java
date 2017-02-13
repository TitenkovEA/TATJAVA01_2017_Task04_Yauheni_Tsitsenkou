package com.epam.catalog.controller;

import com.epam.catalog.controller.command.Command;
import com.epam.catalog.controller.command.impl.AddNews;
import com.epam.catalog.controller.command.impl.FindNews;
import com.epam.catalog.controller.command.impl.WrongRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
class CommandProvider {
    private static final Logger logger = LogManager.getLogger(CommandProvider.class);

    private final Map<CommandName, Command> commandMap = new HashMap<>();

    CommandProvider() {
        commandMap.put(CommandName.ADD_NEWS, new AddNews());
        commandMap.put(CommandName.FIND_NEWS, new FindNews());
        commandMap.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    Command getCommand(String name) {
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = commandMap.get(commandName);
        } catch (NullPointerException | IllegalArgumentException e) {
            command = commandMap.get(CommandName.WRONG_REQUEST);
            logger.error(e.getMessage());
        }

        return command;
    }
}
