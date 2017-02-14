package com.epam.catalog.dao;

import com.epam.catalog.dao.exception.DAOException;

/**
 * Created by Evgeny on 14.02.2017.
 */
public interface DAOResourceManager {
    void initConnectionPool() throws DAOException;

    void clearConnectionPool() throws  DAOException;
}
