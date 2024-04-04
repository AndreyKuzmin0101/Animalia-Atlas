package ru.kpfu.itis.kuzmin.animalswebapp.controller.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.AnimalServices;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.LikeService;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.UsersServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping("/like")
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public String isLiked(@RequestParam("animal") String animal, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");

        if (likeService.findLikeAnimal(userId, animal)) {
            return "1";
        } else {
            return "0";
        }
    }

    @PostMapping("/like")
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public String postLike(@RequestParam("animal") String animal, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");
        return Integer.toString(likeService.like(userId, animal));
    }
}