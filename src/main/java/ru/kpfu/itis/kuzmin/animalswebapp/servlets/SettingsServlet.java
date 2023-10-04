package ru.kpfu.itis.kuzmin.animalswebapp.servlets;


import ru.kpfu.itis.kuzmin.animalswebapp.dto.UserDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.UsersRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.impl.UsersRepositoryJdbcImpl;

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
                user.getAge(), user.getEmail()));

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

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl();
        User userByLogin = null;
        if (!user.getLogin().equals(login) && !"".equals(login)) {
            userByLogin = usersRepository.getByLogin(login);
            if (userByLogin != null) {
                resp.getWriter().println("A user with this username already exists.");
            }
        }
        User userByEmail = null;
        if (!user.getEmail().equals(email) && !"".equals(email)) {
            userByEmail = usersRepository.getByEmail(email);
            if (userByEmail != null) {
                resp.getWriter().println("This email is already occupied.");
            }
        }

        if (!"".equals(firstName)) user.setFirstName(firstName);
        if (!"".equals(lastName)) user.setLastName(lastName);
        if (age != null) user.setAge(age);
        if (!"".equals(email)) user.setEmail(email);
        if (!"".equals(login)) user.setLogin(login);
        if (!"".equals(password)) user.setPassword(password);

        if (userByLogin == null && userByEmail == null) {
            usersRepository.update(user);
            resp.sendRedirect("/logout");
        }
    }
}
