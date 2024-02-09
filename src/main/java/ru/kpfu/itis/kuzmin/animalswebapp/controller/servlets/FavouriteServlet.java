package ru.kpfu.itis.kuzmin.animalswebapp.controller.servlets;

import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.LikeDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.AnimalServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "favouriteServlet", urlPatterns = "/favourite-articles")
public class FavouriteServlet extends HttpServlet {
    private AnimalServices animalServices;

    @Override
    public void init() throws ServletException {
        animalServices = (AnimalServices) getServletContext().getAttribute("animalServices");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = (Integer) req.getSession().getAttribute("id");

        List<Animal> animals = animalServices.getLikedByUserId(id);

        req.setAttribute("animals", animals);
        req.getRequestDispatcher("/view/favourite-articles.ftl").forward(req, resp);
    }
}