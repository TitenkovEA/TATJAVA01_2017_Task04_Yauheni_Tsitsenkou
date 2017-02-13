package com.epam.catalog.dao;

import com.epam.catalog.been.News;
import com.epam.catalog.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public interface NewsDAO {
    void addNews(News news) throws DAOException;
    List<News> findNews(News news) throws DAOException;
}
