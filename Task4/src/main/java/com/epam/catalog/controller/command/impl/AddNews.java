package com.epam.catalog.controller.command.impl;

import com.epam.catalog.been.Category;
import com.epam.catalog.been.News;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.NewsService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class AddNews implements Command {
    private static final Logger logger = LogManager.getLogger(AddNews.class);

    private static final String ERROR_MESSAGE = "Error during addition!";
    private static final String SUCCESS_MESSAGE = "The news has been added";

    public String execute(Map<String, String> request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        News newNews = new News();
        String response = null;

        try {
            NewsService newsService = serviceFactory.getNewsService();

            newNews.setCategory(Category.valueOf(request.get(CATEGORY).toUpperCase()));
            newNews.setTitle(request.get(TITLE));
            newNews.setAuthor(request.get(AUTHOR));

            newsService.addNews(newNews);

            response = SUCCESS_MESSAGE;
        } catch (ServiceException | NullPointerException | IllegalArgumentException e) {
            response = ERROR_MESSAGE;
            logger.error(e);
        }

        return response;
    }
}
