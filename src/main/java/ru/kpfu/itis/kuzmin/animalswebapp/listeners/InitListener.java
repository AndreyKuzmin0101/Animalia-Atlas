package ru.kpfu.itis.kuzmin.animalswebapp.listeners;

import ru.kpfu.itis.kuzmin.animalswebapp.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.CommentDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.impl.*;
import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.services.impl.CommentServicesImpl;
import ru.kpfu.itis.kuzmin.animalswebapp.services.impl.UsersServicesImpl;

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
