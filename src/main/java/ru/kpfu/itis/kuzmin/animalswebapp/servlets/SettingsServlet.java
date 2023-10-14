package ru.kpfu.itis.kuzmin.animalswebapp.servlets;


import ru.kpfu.itis.kuzmin.animalswebapp.dto.UserDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.UsersRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.impl.UsersRepositoryJdbcImpl;
import ru.kpfu.itis.kuzmin.animalswebapp.services.UserServices;

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
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl();
        user = usersRepository.getByLogin((String) req.getSession().getAttribute("login"));

        req.setAttribute("user", new UserDTO(user.getFirstName(), user.getLastName(), user.getLogin(),
                user.getAge(), user.getEmail(), user.getImage()));

        req.getRequestDispatcher("settings.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        Integer age = (!req.getParameter("age").equals("")) ? Integer.parseInt(req.getParameter("age")) : null;
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String result = UserServices.writeUser(user, new User(
                user.getId(), firstName, lastName, age, email, login, password, user.getImage()
        ));
        if (result != null) {
            resp.getWriter().println(result);
        } else {
            resp.sendRedirect("/logout");
        }
    }
}
