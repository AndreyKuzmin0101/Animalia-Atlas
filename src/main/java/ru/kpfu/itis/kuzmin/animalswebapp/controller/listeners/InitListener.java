package ru.kpfu.itis.kuzmin.animalswebapp.controller.listeners;

import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.CommentDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.impl.*;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.impl.CommentServicesImpl;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.impl.UsersServicesImpl;

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
        sce.getServletContext().setAttribute("likeDao", new LikeDaoJdbcImpl());

        UsersDao usersDao = (UsersDao) sce.getServletContext().getAttribute("usersDao");
        sce.getServletContext().setAttribute("usersServices", new UsersServicesImpl(usersDao));
        CommentDao commentDao = (CommentDao) sce.getServletContext().getAttribute("commentDao");
        AnimalDao animalDao = (AnimalDao) sce.getServletContext().getAttribute("animalDao");
        sce.getServletContext().setAttribute("commentServices", new CommentServicesImpl(commentDao, animalDao));

    }
}
