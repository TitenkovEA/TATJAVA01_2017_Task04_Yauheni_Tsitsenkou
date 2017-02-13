package com.epam.catalog.dao.impl;

import com.epam.catalog.been.Category;
import com.epam.catalog.been.News;
import com.epam.catalog.dao.NewsDAO;
import com.epam.catalog.dao.exception.DAOException;
import com.epam.catalog.dao.util.connectionpool.ConnectionPool;
import com.epam.catalog.dao.util.connectionpool.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class SQLNewsDAO implements NewsDAO {
    private static final Logger logger = LogManager.getLogger(SQLNewsDAO.class);

    private static final String CATEGORY_COLUMN = "category";
    private static final String TITLE_COLUMN = "title";
    private static final String AUTHOR_COLUMN = "author";

    private static final String SQL_ADD_NEWS_QUERY = "INSERT INTO news (category, title, author) VALUES (?, ?, ?)";
    private static final String SQL_FIND_NEWS_QUERY = "SELECT * FROM news WHERE category LIKE ? " +
            "AND title LIKE ? " +
            "AND author LIKE ?";

    public void addNews(News news) throws DAOException {
        if (news == null || news.getCategory() == null ||
                news.getTitle() == null || news.getAuthor() == null ) {
            logger.error("NullPointer error!");
            throw new DAOException("NullPointer error!");
        }

        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(SQL_ADD_NEWS_QUERY);
            preparedStatement.setString(1, news.getCategory().toString());
            preparedStatement.setString(2, news.getTitle());
            preparedStatement.setString(3, news.getAuthor());

            if (!preparedStatement.execute()) {
                logger.error("ERROR");
                throw new DAOException();
            }
        } catch (ConnectionPoolException | SQLException e) {
            logger.error(e.getStackTrace());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getStackTrace());
                }
            }
        }
    }

    public List<News> findNews(News news) throws DAOException {
        if (news == null || news.getCategory() == null ||
                news.getTitle() == null || news.getAuthor() == null ) {
            logger.error("NullPointer error!");
            throw new DAOException("NullPointer error!");
        }

        ArrayList<News> newsList = new ArrayList<>();
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(SQL_FIND_NEWS_QUERY);
            preparedStatement.setString(1,"%" + news.getCategory().toString() + "%");
            preparedStatement.setString(2,"%" + news.getTitle() + "%");
            preparedStatement.setString(3,"%" + news.getAuthor() + "%");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newsList.add(getNewsFromResultSetRow(resultSet));
            }
        } catch (ConnectionPoolException | SQLException e) {
            logger.error(e.getStackTrace());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                   // logger.error(e.getStackTrace());
                }
            }
        }

        return newsList;
    }

    private News getNewsFromResultSetRow(ResultSet resultSet) throws SQLException {
        Category category = Category.valueOf(resultSet.getString(CATEGORY_COLUMN));
        String title = resultSet.getString(TITLE_COLUMN);
        String author = resultSet.getString(AUTHOR_COLUMN);

        return new News(category, title, author);
    }
}
