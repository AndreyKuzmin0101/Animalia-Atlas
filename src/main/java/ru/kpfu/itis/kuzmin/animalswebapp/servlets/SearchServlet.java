package ru.kpfu.itis.kuzmin.animalswebapp.servlets;

import ru.kpfu.itis.kuzmin.animalswebapp.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.CategoryDao;
import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.models.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "searchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        String categoryEnName = req.getParameter("category");

        System.out.println(query + " " + categoryEnName);

        List<Animal> animals = new ArrayList<>();

        CategoryDao categoryDao = (CategoryDao) req.getServletContext().getAttribute("categoryDao");
        AnimalDao animalDao = (AnimalDao) req.getServletContext().getAttribute("animalDao");

        List<Animal> animalsByCategory;
        if (categoryEnName != null && !categoryEnName.equals("")) {
            Category category = categoryDao.getByEnName(categoryEnName);
            animalsByCategory = animalDao.getByCategoryId(category.getId());
        } else {
            animalsByCategory = animalDao.getAll();
        }

        if (query != null && !query.equals("")) {
            for (Animal animal: animalsByCategory) {
                if (animal.getName().toLowerCase().contains(query.toLowerCase())) {
                    animals.add(animal);
                }
            }
        } else {
            animals = animalsByCategory;
        }

        req.setAttribute("animals", animals);
        req.getRequestDispatcher("search.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
