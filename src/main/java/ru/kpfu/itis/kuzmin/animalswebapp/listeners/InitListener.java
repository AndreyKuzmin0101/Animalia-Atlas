package ru.kpfu.itis.kuzmin.animalswebapp.listeners;

import ru.kpfu.itis.kuzmin.animalswebapp.dao.impl.AnimalDaoJdbcImpl;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.impl.CategoryDaoJdbcImpl;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.impl.CommentDaoJdbcImpl;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.impl.UsersDaoJdbcImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("animalDao", new AnimalDaoJdbcImpl());
        sce.getServletContext().setAttribute("commentDao", new CommentDaoJdbcImpl());
        sce.getServletContext().setAttribute("usersDao", new UsersDaoJdbcImpl());
        sce.getServletContext().setAttribute("categoryDao", new CategoryDaoJdbcImpl());
    }
}
