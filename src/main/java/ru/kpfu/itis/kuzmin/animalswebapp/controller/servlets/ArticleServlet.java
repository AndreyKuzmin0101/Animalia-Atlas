package ru.kpfu.itis.kuzmin.animalswebapp.controller.servlets;

import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.AnimalDao;

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
        if (animal == null) {
            resp.sendRedirect("/view/404.ftl");
            return;
        }
        req.setAttribute("image", animal.getImage());
        req.setAttribute("likes", String.valueOf(animal.getLikes()));
        if (req.getSession(false) != null) req.getSession(false).setAttribute("animal_id", animal.getId());
        req.getRequestDispatcher("/view/" + req.getServletPath() + "/" + req.getParameter("animal") + ".ftl").forward(req, resp);
    }
}
