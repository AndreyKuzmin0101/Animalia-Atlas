package ru.kpfu.itis.kuzmin.animalswebapp.servlets;

import ru.kpfu.itis.kuzmin.animalswebapp.dto.UserDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.services.UsersServices;
import ru.kpfu.itis.kuzmin.animalswebapp.services.impl.UsersServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "usersServlet", urlPatterns = "/users/*")
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersServices usersServices = (UsersServices) req.getServletContext().getAttribute("usersServices");
        if (req.getParameter("id") != null) {

            UserDTO user = usersServices.getById(Integer.valueOf(req.getParameter("id")));
            req.setAttribute("user", user);
            req.getRequestDispatcher("user_profile.ftl").forward(req, resp);
        } else {

            List<UserDTO> users = usersServices.getAll();
            System.out.println(users);
            req.setAttribute("users", users);
            req.getRequestDispatcher("list_users.ftl").forward(req, resp);
        }

    }
}
