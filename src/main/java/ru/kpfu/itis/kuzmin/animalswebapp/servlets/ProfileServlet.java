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

@WebServlet(name = "profileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl();
        User user = usersRepository.getByLogin((String) req.getSession(false).getAttribute("login"));


        req.setAttribute("user", new UserDTO(user.getFirstName(), user.getLastName(), user.getLogin(),
                user.getAge(), user.getEmail(), user.getImage()));

        req.getRequestDispatcher("profile.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/settings");
    }
}
