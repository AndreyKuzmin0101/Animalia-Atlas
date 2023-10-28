package ru.kpfu.itis.kuzmin.animalswebapp.controller.servlets;


import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.UserDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.UsersServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "settingsServlet", urlPatterns = "/settings")
public class SettingsServlet extends HttpServlet {
    private User user;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersDao usersDao = (UsersDao) req.getServletContext().getAttribute("usersDao");
        user = usersDao.getByLogin((String) req.getSession().getAttribute("login"));

        req.setAttribute("user", new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(),
                user.getAge(), user.getEmail(), user.getImage()));

        req.getRequestDispatcher("/view/settings.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        Integer age = (!req.getParameter("age").equals("")) ? Integer.parseInt(req.getParameter("age")) : 0;
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UsersServices usersServices = (UsersServices) req.getServletContext().getAttribute("usersServices");
        String result = usersServices.updateUser(user, new User(
                user.getId(), firstName, lastName, age, email, login, password, user.getImage())
        );
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Type", "text/plain; charset=utf-8");
        if (result != null) {
            resp.getWriter().println(result);
        } else {
            resp.sendRedirect("/logout");
        }
    }
}
