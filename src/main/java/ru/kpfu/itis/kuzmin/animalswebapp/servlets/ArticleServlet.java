package ru.kpfu.itis.kuzmin.animalswebapp.servlets;

import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.AnimalDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "articleServlet", urlPatterns = "/articles")
public class ArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AnimalDao animalDao = (AnimalDao) req.getServletContext().getAttribute("animalDao");
        Animal animal = animalDao.getByEnName(req.getParameter("animal"));
        req.setAttribute("image", animal.getImage());
        if (req.getSession(false) != null) req.getSession(false).setAttribute("animal_id", animal.getId());
        req.getRequestDispatcher(req.getServletPath() + "/" + req.getParameter("animal") + ".ftl").forward(req, resp);
    }
}
