package ru.kpfu.itis.kuzmin.animalswebapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// TODO
// Данную логику remember me я просто перенёс из семестровки
// По хорошему нужно использовать токены, например JWT
@RequiredArgsConstructor
public class CookieAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("login".equalsIgnoreCase(cookie.getName())) {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(cookie.getValue());
                        User user = ((PlainUserDetails)userDetails).getUser();

                        HttpSession session = request.getSession();
                        session.setAttribute("login", user.getLogin());
                        session.setAttribute("id", user.getId());
                        session.setAttribute("image", user.getImage());

                        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(token);
                    }
                }
            }

        }
        filterChain.doFilter(request, response);
    }
}
