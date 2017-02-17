package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Category;
import com.epam.catalog.bean.News;
import com.epam.catalog.dao.exception.DAOException;
import com.epam.catalog.dao.util.connectionpool.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.List;

/**
 * Created by Evgeny on 15.02.2017.
 */
public class SQLNewsDAOTest {
    private SQLNewsDAO sqlNewsDAO;

    @BeforeClass
    public void setUp() throws Exception {
        sqlNewsDAO = new SQLNewsDAO();
        ConnectionPool.getInstance().initPoolData();
    }

    @DataProvider(name = "legalNews")
    private Object[][] getLegalNews() {
        return new Object[][] {
                {new News(Category.BOOK, "testTitle", "testAuthor")},
                {new News(Category.FILM, "testTitle", "testAuthor")},
                {new News(Category.DISK, "testTitle", "testAuthor")},
        };
    }

    @Test(dataProvider = "legalNews")
    public void positiveAddNews(News news) throws Exception {
        sqlNewsDAO.addNews(news);
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/catalog?useSSL=false", "root", "root"
        );
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM news WHERE category = ? AND title = ? AND author = ?"
        );
        preparedStatement.setString(1, news.getCategory().toString());
        preparedStatement.setString(2, news.getTitle());
        preparedStatement.setString(3, news.getAuthor());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Assert.assertEquals(resultSet.getString("category"), news.getCategory().toString());
            Assert.assertEquals(news.getTitle(), resultSet.getString("title"));
            Assert.assertEquals(news.getAuthor(), resultSet.getString("author"));
        }
    }

    @Test(dataProvider = "legalNews")
    public void positiveFindNews(News news) throws Exception {
        List<News> list = sqlNewsDAO.findNews(news);
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/catalog?useSSL=false", "root", "root"
        );
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM news WHERE category = ? AND title = ? AND author = ?"
        );
        preparedStatement.setString(1, news.getCategory().toString());
        preparedStatement.setString(2, news.getTitle());
        preparedStatement.setString(3, news.getAuthor());

        ResultSet resultSet = preparedStatement.executeQuery();
        News newsForCheck = null;
        while (resultSet.next()) {
            Category category = Category.valueOf(resultSet.getString("category"));
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            newsForCheck = new News(category, title, author);
            Assert.assertTrue(list.contains(newsForCheck));
        }
    }

    @AfterClass
    public void setDown() throws Exception {
        ConnectionPool.getInstance().clearConnectionQueue();
    }

}