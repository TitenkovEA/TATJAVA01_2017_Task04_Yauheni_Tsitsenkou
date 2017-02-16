package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Category;
import com.epam.catalog.bean.News;
import com.epam.catalog.dao.NewsDAO;
import com.epam.catalog.dao.exception.DAOException;
import com.epam.catalog.dao.util.JDBCResourceManager;
import com.epam.catalog.dao.util.connectionpool.ConnectionPool;
import com.epam.catalog.dao.util.connectionpool.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class SQLNewsDAO implements NewsDAO {
    private static final Logger logger = LogManager.getLogger(SQLNewsDAO.class);

    private static final String CATEGORY_COLUMN = "category";
    private static final String TITLE_COLUMN = "title";
    private static final String AUTHOR_COLUMN = "author";

    private static final String EXECUTION_ERROR = "Error while executing query!";

    private static final String SQL_ADD_NEWS_QUERY =
            "INSERT INTO news (category, title, author) VALUES (?, ?, ?)";

    private static final String SQL_FIND_NEWS_QUERY =
            "SELECT * FROM news WHERE category LIKE ? AND title LIKE ? AND author LIKE ?";

    private static final char SIGN_TO_DEFINE_WILDCARDS = '%';

    public void addNews(News news) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        PreparedStatement preparedStatement = null;

        try (Connection connection = connectionPool.takeConnection()) {
            preparedStatement = connection.prepareStatement(SQL_ADD_NEWS_QUERY);

            preparedStatement.setString(1, news.getCategory().toString());
            preparedStatement.setString(2, news.getTitle());
            preparedStatement.setString(3, news.getAuthor());

            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DAOException(EXECUTION_ERROR, e);
        } finally {
            try {
                JDBCResourceManager.closeJdbcResources(preparedStatement, null);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    public List<News> findNews(News news) throws DAOException {
        List<News> newsList = new LinkedList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try (Connection connection = connectionPool.takeConnection()) {
            preparedStatement = connection.prepareStatement(SQL_FIND_NEWS_QUERY);

            preparedStatement.setString(1,
                    SIGN_TO_DEFINE_WILDCARDS + news.getCategory().toString() + SIGN_TO_DEFINE_WILDCARDS);
            preparedStatement.setString(2,
                    SIGN_TO_DEFINE_WILDCARDS + news.getTitle() + SIGN_TO_DEFINE_WILDCARDS);
            preparedStatement.setString(3,
                    SIGN_TO_DEFINE_WILDCARDS + news.getAuthor() + SIGN_TO_DEFINE_WILDCARDS);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newsList.add(getNewsFromResultSetRow(resultSet));
            }
        } catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DAOException(EXECUTION_ERROR, e);
        } finally {
            try {
                JDBCResourceManager.closeJdbcResources(preparedStatement, resultSet);
            } catch (SQLException e) {
                logger.error(e);
            }
        }

        return new ArrayList<>(newsList);
    }

    private News getNewsFromResultSetRow(ResultSet resultSet) throws SQLException {
        Category category = Category.valueOf(resultSet.getString(CATEGORY_COLUMN));
        String title = resultSet.getString(TITLE_COLUMN);
        String author = resultSet.getString(AUTHOR_COLUMN);

        return new News(category, title, author);
    }
}
