package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Category;
import com.epam.catalog.bean.News;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.NewsService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class FindNews implements Command {
    private static final Logger logger = LogManager.getLogger(FindNews.class);

    private static final String ERROR_MESSAGE = "Error during searching!";

    public String execute(Map<String, String> request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        NewsService newsService = serviceFactory.getNewsService();
        News newNews = new News();
        StringBuilder result = new StringBuilder();
        String response = null;

        try {
            newNews.setCategory(Category.valueOf(request.get(CATEGORY).toUpperCase()));
            newNews.setTitle(request.get(TITLE));
            newNews.setAuthor(request.get(AUTHOR));

            List<News> newsList = newsService.findNews(newNews);

            for (News news :
                    newsList) {
                result.append(news).append("\n");
            }

            response = result.toString();
        } catch (ServiceException | NullPointerException | IllegalArgumentException e) {
            response = ERROR_MESSAGE;
            logger.error(e);
        }

        return response;
    }
}
