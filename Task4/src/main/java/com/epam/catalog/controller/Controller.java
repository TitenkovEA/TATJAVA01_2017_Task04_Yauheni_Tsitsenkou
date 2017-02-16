package com.epam.catalog.controller;

import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.ResourceManagerService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public final class Controller {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    private static final CommandProvider provider = new CommandProvider();
    private static final String COMMAND_NAME = "COMMAND_NAME";

    public String executeCommand(Map<String, String> request) {
        String commandName = request.get(COMMAND_NAME);

        Command command = provider.getCommand(commandName);

        return command.execute(request);
    }

    public void initResource() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ResourceManagerService resourceManagerService = serviceFactory.getResourceManagerService();

        try {
            resourceManagerService.initDAOResource();
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    public void clearResource() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ResourceManagerService resourceManagerService = serviceFactory.getResourceManagerService();

        try {
            resourceManagerService.clearDAOResource();
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
