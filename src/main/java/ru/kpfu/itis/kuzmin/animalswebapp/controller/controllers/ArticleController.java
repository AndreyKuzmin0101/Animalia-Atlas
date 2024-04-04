package ru.kpfu.itis.kuzmin.animalswebapp.controller.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.AnimalDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.AnimalServices;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.LikeService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final AnimalServices animalService;

    @GetMapping("/articles")
    public String getArticle(@RequestParam("animal") String animalEnName, Model model) {
        AnimalDTO animal = animalService.getByEnName(animalEnName);

        model.addAttribute("image", animal.getImage());
        model.addAttribute("likes", String.valueOf(animal.getLikes()));
        String viewName = "articles/%s".formatted(animalEnName);
        return viewName;
    }
}
