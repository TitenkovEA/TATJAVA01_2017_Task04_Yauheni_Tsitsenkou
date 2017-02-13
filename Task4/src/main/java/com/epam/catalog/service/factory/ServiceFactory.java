package com.epam.catalog.service.factory;

import com.epam.catalog.service.NewsService;
import com.epam.catalog.service.impl.ImplNewsService;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final NewsService implNewsService = new ImplNewsService();

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public NewsService getNewsService() {
        return implNewsService;
    }
}
