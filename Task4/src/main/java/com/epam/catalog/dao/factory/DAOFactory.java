package com.epam.catalog.dao.factory;

import com.epam.catalog.dao.DAOResourceManager;
import com.epam.catalog.dao.NewsDAO;
import com.epam.catalog.dao.impl.DAOResourceManagerImpl;
import com.epam.catalog.dao.impl.SQLNewsDAO;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final NewsDAO txtNewsDAO = new SQLNewsDAO();
    private final DAOResourceManager daoResourceManager = new DAOResourceManagerImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public NewsDAO getNewsDAO() {
        return txtNewsDAO;
    }

    public DAOResourceManager getDaoResourceManager() {
        return daoResourceManager;
    }
}
