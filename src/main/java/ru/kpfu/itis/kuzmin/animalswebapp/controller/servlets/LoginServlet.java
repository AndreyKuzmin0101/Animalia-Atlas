package ru.kpfu.itis.kuzmin.animalswebapp.controller.servlets;

import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.PasswordUtil;

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
            resp.sendRedirect("/profile");
        } else {
            Cookie[] cookies = req.getCookies();
            boolean authentication = false;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("login".equalsIgnoreCase(cookie.getName())) {
                        UsersDao usersDao = (UsersDao) req.getServletContext().getAttribute("usersDao");
                        User user = usersDao.getByLogin(cookie.getValue());
                        HttpSession httpSession = req.getSession(true);
                        httpSession.setAttribute("login", user.getLogin());
                        httpSession.setAttribute("id", user.getId());
                        httpSession.setAttribute("image", user.getImage());
                        authentication = true;
                        resp.getWriter().println("Login successful!");
                        resp.sendRedirect("/profile");
                        break;
                    }
                }
            }

            if (!authentication) req.getRequestDispatcher("/view/login.html").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String safePassword = PasswordUtil.encrypt(password);

        UsersDao usersDao = (UsersDao) req.getServletContext().getAttribute("usersDao");
        User user = usersDao.getByLogin(login);

        if (user != null && user.getPassword().equals(safePassword)) {
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("login", login);
            httpSession.setAttribute("id", user.getId());
            httpSession.setAttribute("image", user.getImage());
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
