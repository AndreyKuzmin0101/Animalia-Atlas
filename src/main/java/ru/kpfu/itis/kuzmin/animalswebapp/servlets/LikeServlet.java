package ru.kpfu.itis.kuzmin.animalswebapp.servlets;

import ru.kpfu.itis.kuzmin.animalswebapp.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.LikeDao;
import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "likeServlet", urlPatterns = "/like")
public class LikeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer animalId = (Integer) req.getSession(false).getAttribute("animal_id");
        Integer userId = (Integer) req.getSession(false).getAttribute("id");

        LikeDao likeDao = (LikeDao) req.getSession(false).getServletContext().getAttribute("likeDao");

        if (likeDao.findLikeAnimal(userId, animalId)) {
            resp.getWriter().print(1);
        } else {
            resp.getWriter().print(0);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer animalId = (Integer) req.getSession(false).getAttribute("animal_id");
        Integer userId = (Integer) req.getSession(false).getAttribute("id");

        LikeDao likeDao = (LikeDao) req.getSession(false).getServletContext().getAttribute("likeDao");
        AnimalDao animalDao = (AnimalDao) req.getSession(false).getServletContext().getAttribute("animalDao");

        Animal animal = animalDao.getById(animalId);
        Integer likes = animal.getLikes();

        if (likeDao.findLikeAnimal(userId, animalId)){
            likeDao.deleteLikeAnimal(userId, animalId);
            likes = likes - 1;
            animalDao.updateLikes(animalId, likes);
        } else {
            likeDao.saveLikeAnimal(userId, animalId);
            likes = likes + 1;
            animalDao.updateLikes(animalId, likes);
        }
        resp.getWriter().print(likes);
    }
}