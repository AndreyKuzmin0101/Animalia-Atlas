package ru.kpfu.itis.kuzmin.animalswebapp.servlets;

import ru.kpfu.itis.kuzmin.animalswebapp.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.services.UsersServices;
import ru.kpfu.itis.kuzmin.animalswebapp.services.impl.UsersServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "registerServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession(false) != null) {
            resp.getWriter().println("You are already registered");
        }
        else {
            req.getRequestDispatcher("register.html").forward(req, resp);
        }
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

        String result = usersServices.saveUser(new User(
                null, firstName, lastName, age, email, login, password, "https://res.cloudinary.com/debjgvnym/image/upload/bjgclwsmr3lkkpsjeebg.png")
        );

        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Type", "text/plain; charset=utf-8");
        if (result != null) {
            resp.getWriter().println(result);
        } else {
            resp.sendRedirect("/login");
        }
    }
}
