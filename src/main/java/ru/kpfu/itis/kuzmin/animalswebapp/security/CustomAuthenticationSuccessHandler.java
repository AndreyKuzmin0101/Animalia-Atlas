package ru.kpfu.itis.kuzmin.animalswebapp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            User user = ((PlainUserDetails) userDetails).getUser();
            HttpSession session = request.getSession();
            session.setAttribute("login", user.getLogin());
            session.setAttribute("id", user.getId());
            session.setAttribute("image", user.getImage());
            // Данную логику remember me я просто перенёс из семестровки
            // По хорошему нужно использовать токены, например JWT
            if ("on".equals(request.getParameter("remember"))) {
                Cookie cookie = new Cookie("login", user.getLogin());
                cookie.setMaxAge(24*60*60);
                response.addCookie(cookie);
            }

            response.sendRedirect(request.getContextPath() + "/profile");
        }
    }
}
