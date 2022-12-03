package ru.kpfu.itis;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.jdbc.SimpleDataSource;
import ru.kpfu.itis.repositories.CommentRepositoryImpl;
import ru.kpfu.itis.repositories.FlatRepositoryImpl;
import ru.kpfu.itis.repositories.UserRepositoryImpl;
import ru.kpfu.itis.services.UserService;

import javax.sql.DataSource;

@WebListener
public class MainContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            DataSource dataSource = new SimpleDataSource();
            servletContextEvent.getServletContext().setAttribute("flatRepository",new FlatRepositoryImpl(dataSource));
            servletContextEvent.getServletContext().setAttribute("userRepository",new UserRepositoryImpl(dataSource));
            servletContextEvent.getServletContext().setAttribute("commentRepository",new CommentRepositoryImpl(dataSource));
            servletContextEvent.getServletContext().setAttribute("userService",new UserService());
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
