package ru.kpfu.itis.jdbc;

import ru.kpfu.itis.exceptions.DbException;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class SimpleDataSource implements DataSource {

    private Connection connection;

    public SimpleDataSource() throws DbException {
        openConnection();
    }

    private void openConnection() throws DbException {
        Properties properties = new Properties();
        try {
            properties.load(SimpleDataSource.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password")
            );
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new DbException("Can't connect to DB",e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if(this.connection == null || this.connection.isClosed()){
            try {
                openConnection();
            } catch (DbException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
