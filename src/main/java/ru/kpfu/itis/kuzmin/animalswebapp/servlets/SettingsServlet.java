package ru.kpfu.itis.kuzmin.animalswebapp.servlets;


import ru.kpfu.itis.kuzmin.animalswebapp.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.UsersRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.impl.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "settingsServlet", urlPatterns = "/profile/settings")
public class SettingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("settings.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
