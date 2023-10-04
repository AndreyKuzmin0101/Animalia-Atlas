package ru.kpfu.itis.kuzmin.animalswebapp.servlets;

import ru.kpfu.itis.kuzmin.animalswebapp.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.UsersRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.impl.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession(false) != null) {
            resp.getWriter().println("You have already authenticated.");
        } else {
            Cookie[] cookies = req.getCookies();
            boolean authentication = false;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("login".equalsIgnoreCase(cookie.getName())) {
                        HttpSession httpSession = req.getSession(true);
                        httpSession.setAttribute("login", cookie.getValue());
                        authentication = true;
                        resp.getWriter().println("Login successful!");
                    }
                }
            }

            if (!authentication) req.getRequestDispatcher("login.html").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");


        UsersRepository usersRepository = new UsersRepositoryJdbcImpl();
        User user = usersRepository.getByLogin(login);
        if (user != null && user.getLogin().equals(login) && user.getPassword().equals(password)) {
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("login", login);
            httpSession.setMaxInactiveInterval(60*60);

            if ("on".equals(req.getParameter("remember"))) {
                Cookie cookie = new Cookie("login", login);
                cookie.setMaxAge(24*60*60);
                resp.addCookie(cookie);
            }


            resp.sendRedirect("/profile");
        } else {
            resp.sendRedirect("/login");
        }
    }
}
