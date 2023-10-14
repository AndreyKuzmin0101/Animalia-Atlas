package ru.kpfu.itis.kuzmin.animalswebapp.servlets;

import ru.kpfu.itis.kuzmin.animalswebapp.dto.AnimalDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.AnimalRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.impl.AnimalRepositoryJdbcImpl;

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

        AnimalRepository animalRepository = new AnimalRepositoryJdbcImpl();
        Animal lion = animalRepository.getByEnName("lion");
        Animal cat = animalRepository.getByEnName("cat");
        Animal fish = animalRepository.getByEnName("fish");

        req.setAttribute("lion", new AnimalDTO(lion.getName(), lion.getDescription(), lion.getImage(), lion.getEnName()));
        req.setAttribute("cat", new AnimalDTO(cat.getName(), cat.getDescription(), cat.getImage(), cat.getEnName()));
        req.setAttribute("fish", new AnimalDTO(fish.getName(), fish.getDescription(), fish.getImage(), fish.getEnName()));

        req.getRequestDispatcher("index.ftl").forward(req, resp);
    }
}
