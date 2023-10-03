package ru.kpfu.itis.kuzmin.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession(false) != null) {
            resp.getWriter().println("You have already authenticated.");
        } else {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("username".equalsIgnoreCase(cookie.getName())) {
                        HttpSession httpSession = req.getSession();
                        httpSession.setAttribute("username", cookie.getValue());

                        resp.getWriter().println("Login successful!");
                    }
                }
            } else {
                req.getRequestDispatcher("login.html").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (LOGIN.equalsIgnoreCase(login) && PASSWORD.equals(password)) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("username", login);
            httpSession.setMaxInactiveInterval(60*60);

            Cookie cookie = new Cookie("username", login);
            cookie.setMaxAge(24*60*60);
            resp.addCookie(cookie);

            //...
        } else {
            resp.sendRedirect("/login");
        }
    }
}
