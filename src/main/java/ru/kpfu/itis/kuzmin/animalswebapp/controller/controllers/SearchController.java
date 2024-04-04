package ru.kpfu.itis.kuzmin.animalswebapp.controller.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.AnimalDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Category;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.AnimalServices;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class SearchController {

    private final AnimalServices animalService;
    private final CategoryService categoryService;

    @GetMapping("/search")
    protected String getResult(@RequestParam(value = "query", required = false) String query, @RequestParam(value = "category", required = false) String categoryEnName, Model model) {
        List<AnimalDTO> animals = animalService.getByQueryAndCategory(query, categoryEnName);

        model.addAttribute("animals", animals);
        return "search";
    }
}
