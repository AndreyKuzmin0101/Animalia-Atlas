package ru.kpfu.itis.kuzmin.animalswebapp.controller.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.AnimalServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FavouriteController {
    private final AnimalServices animalService;

    @GetMapping("/favourite-articles")
    @PreAuthorize("hasRole('USER')")
    public String getFavourite(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("id");
        List<Animal> animals = animalService.getLikedByUserId(id);
        model.addAttribute("animals", animals);
        return "favourite-articles";
    }
}