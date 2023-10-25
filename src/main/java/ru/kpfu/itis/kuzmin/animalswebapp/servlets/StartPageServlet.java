package ru.kpfu.itis.kuzmin.animalswebapp.servlets;


import ru.kpfu.itis.kuzmin.animalswebapp.dto.AnimalDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.impl.AnimalDaoJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "startPageServlet", urlPatterns = "")
public class StartPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AnimalDao animalDao = (AnimalDao) req.getServletContext().getAttribute("animalDao");
        Animal lion = animalDao.getByEnName("lion");
        Animal japaneseSable = animalDao.getByEnName("japanese sable");
        Animal stripedHyena = animalDao.getByEnName("striped hyena");

        req.setAttribute("lion", new AnimalDTO(lion.getName(), lion.getDescription(), lion.getImage(), lion.getEnName(), lion.getLikes()));
        req.setAttribute("japaneseSable", new AnimalDTO(japaneseSable.getName(), japaneseSable.getDescription(), japaneseSable.getImage(), japaneseSable.getEnName(), japaneseSable.getLikes()));
        req.setAttribute("stripedHyena", new AnimalDTO(stripedHyena.getName(), stripedHyena.getDescription(), stripedHyena.getImage(), stripedHyena.getEnName(), stripedHyena.getLikes()));

        req.getRequestDispatcher("index.ftl").forward(req, resp);
    }
}
