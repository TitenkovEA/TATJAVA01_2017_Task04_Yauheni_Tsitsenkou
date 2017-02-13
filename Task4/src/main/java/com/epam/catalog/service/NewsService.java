package com.epam.catalog.service;

import com.epam.catalog.been.News;
import com.epam.catalog.service.exception.ServiceException;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public interface NewsService {
    void addNews(News news) throws ServiceException;
    String findNews(News news) throws ServiceException;
}
