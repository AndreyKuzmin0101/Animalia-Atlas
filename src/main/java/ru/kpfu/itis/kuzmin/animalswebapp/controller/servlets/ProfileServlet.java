package ru.kpfu.itis.kuzmin.animalswebapp.controller.servlets;

import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.UserDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "profileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    private UsersDao usersDao;
    @Override
    public void init() throws ServletException {
        usersDao = (UsersDao) getServletContext().getAttribute("usersDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = usersDao.getByLogin((String) req.getSession(false).getAttribute("login"));


        req.setAttribute("user", new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(),
                user.getAge(), user.getEmail(), user.getImage()));

        req.getRequestDispatcher("/view/profile.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/settings");
    }
}
