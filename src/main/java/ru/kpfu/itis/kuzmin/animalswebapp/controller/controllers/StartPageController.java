package ru.kpfu.itis.kuzmin.animalswebapp.controller.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.AnimalDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.AnimalServices;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.LikeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequiredArgsConstructor
public class StartPageController {
    private final AnimalServices animalService;

    @GetMapping("/")
    public String start(Model model) {
        AnimalDTO lion = animalService.getByEnName("lion");
        AnimalDTO japaneseSable = animalService.getByEnName("japanese sable");
        AnimalDTO stripedHyena = animalService.getByEnName("striped hyena");

        model.addAttribute("lion", lion);
        model.addAttribute("japaneseSable", japaneseSable);
        model.addAttribute("stripedHyena", stripedHyena);

        return "index";
    }
}
