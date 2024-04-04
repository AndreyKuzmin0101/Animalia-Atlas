package ru.kpfu.itis.kuzmin.animalswebapp.controller.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.security.PlainUserDetails;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

//    @GetMapping("/login")
//    public void autologin() {
//        if (req.getSession(false) != null) {
//            resp.getWriter().println("You have already authenticated.");
//            resp.sendRedirect("/profile");
//        } else {
//            Cookie[] cookies = req.getCookies();
//            boolean authentication = false;
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    if ("login".equalsIgnoreCase(cookie.getName())) {
//                        User user = usersService.getByLogin(cookie.getValue());
//                        HttpSession httpSession = req.getSession(true);
//                        httpSession.setAttribute("login", user.getLogin());
//                        httpSession.setAttribute("id", user.getId());
//                        httpSession.setAttribute("image", user.getImage());
//                        authentication = true;
//                        resp.sendRedirect("/profile");
//                        break;
//                    }
//                }
//            }
//
//            if (!authentication) req.getRequestDispatcher("/view/login.html").forward(req, resp);
//        }
//    }
//            if ("on".equals(req.getParameter("remember"))) {
//                Cookie cookie = new Cookie("login", login);
//                cookie.setMaxAge(24 * 60 * 60);
//                resp.addCookie(cookie);
//            }
    @GetMapping("/login")
    public String getLoginForm() {
        return "login";
    }

}
