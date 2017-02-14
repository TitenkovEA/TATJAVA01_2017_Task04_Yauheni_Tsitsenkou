package com.epam.catalog.service.impl;

import com.epam.catalog.dao.DAOResourceManager;
import com.epam.catalog.dao.exception.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import com.epam.catalog.service.ResourceManagerService;
import com.epam.catalog.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Evgeny on 15.02.2017.
 */
public class ResourceManagerServiceImpl implements ResourceManagerService {
    private static final Logger logger = LogManager.getLogger(ResourceManagerServiceImpl.class);

    private static final String DAO_RESOURCE_INIT_ERROR = "DAOException while init DAO resource.";
    private static final String DAO_RESOURCE_CLEAR_ERROR = "DAOException while clear DAO resource.";

    public void initDAOResource() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOResourceManager daoResourceManager = daoFactory.getDaoResourceManager();
        try {
            daoResourceManager.initConnectionPool();
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(DAO_RESOURCE_INIT_ERROR, e);
        }
    }

    public void clearDAOResource() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOResourceManager daoResourceManager = daoFactory.getDaoResourceManager();
        try {
            daoResourceManager.clearConnectionPool();
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(DAO_RESOURCE_CLEAR_ERROR, e);
        }
    }
}
