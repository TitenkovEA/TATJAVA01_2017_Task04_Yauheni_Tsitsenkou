package com.epam.catalog.service.impl;

import com.epam.catalog.bean.News;
import com.epam.catalog.dao.NewsDAO;
import com.epam.catalog.dao.exception.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import com.epam.catalog.service.NewsService;
import com.epam.catalog.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class NewsServiceImpl implements NewsService {
    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    private static final String INCORRECT_NEWS_ERROR = "Incorrect news. NullPointer error!";

    public void addNews(News news) throws ServiceException {
        if (isNull(news)) {
            throw new ServiceException(INCORRECT_NEWS_ERROR);
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoFactory.getNewsDAO();
        try {
            newsDAO.addNews(news);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public List<News> findNews(News news) throws ServiceException {
        if (isNull(news)) {
            throw new ServiceException(INCORRECT_NEWS_ERROR);
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoFactory.getNewsDAO();
        List<News> foundedNews;
        try {
            foundedNews = newsDAO.findNews(news);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

        return foundedNews;
    }

    private boolean isNull(News news) {
        boolean result = false;

        if (news == null) {
            result = true;
        } else if (news.getCategory() == null ||
                news.getTitle() == null ||
                news.getAuthor() == null) {
            result = true;
        }

        return result;
    }
}
