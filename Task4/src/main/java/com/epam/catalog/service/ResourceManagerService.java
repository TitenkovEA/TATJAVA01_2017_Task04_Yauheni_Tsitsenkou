package com.epam.catalog.service;

import com.epam.catalog.service.exception.ServiceException;

/**
 * Created by Evgeny on 15.02.2017.
 */
public interface ResourceManagerService {
    void initDAOResource() throws ServiceException;

    void clearDAOResource() throws ServiceException;
}
