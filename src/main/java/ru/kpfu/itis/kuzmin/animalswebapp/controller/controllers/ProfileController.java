package ru.kpfu.itis.kuzmin.animalswebapp.controller.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.UserDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.UsersServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final UsersServices usersService;


    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public String profile(HttpSession session, Model model) throws ServletException, IOException {
        UserDTO user = usersService.getByLogin((String) session.getAttribute("login"));

        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public String settings() {
        return "redirect:/settings";
    }
}
