package com.epam.catalog.service;

import com.epam.catalog.bean.News;
import com.epam.catalog.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public interface NewsService {
    void addNews(News news) throws ServiceException;

    List<News> findNews(News news) throws ServiceException;
}
