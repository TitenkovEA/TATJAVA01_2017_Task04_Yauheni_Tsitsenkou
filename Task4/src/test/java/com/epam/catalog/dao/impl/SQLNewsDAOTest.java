package com.epam.catalog.dao.impl;

import com.epam.catalog.been.Category;
import com.epam.catalog.been.News;
import com.epam.catalog.dao.exception.DAOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Evgeny on 15.02.2017.
 */
public class SQLNewsDAOTest {
    private SQLNewsDAO sqlNewsDAO;

    @BeforeClass
    public void setUp() {
        sqlNewsDAO = new SQLNewsDAO();
    }

    @DataProvider(name = "illegalNews")
    public Object[][] getIllegalNewsToFind() {
        return new Object[][] {
                {null},
                {new News()},
                {new News(null, "", "")},
                {new News(Category.BOOK, null, "")},
                {new News(Category.BOOK, "", null)},
                {new News(Category.BOOK, null, null)},
        };
    }

    @Test(expectedExceptions = DAOException.class, dataProvider = "illegalNews")
    public void negativeAddNews(News news) throws Exception {
        sqlNewsDAO.addNews(news);
    }

    @Test(expectedExceptions = DAOException.class, dataProvider = "illegalNews")
    public void negativeFindNews(News news) throws Exception {
        sqlNewsDAO.findNews(news);
    }

}