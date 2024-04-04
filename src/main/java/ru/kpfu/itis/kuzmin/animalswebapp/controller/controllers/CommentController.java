package ru.kpfu.itis.kuzmin.animalswebapp.controller.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.CommentDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Comment;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.CommentServices;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.UsersServices;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;


@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentServices commentService;

    @GetMapping("/comments")
    @ResponseBody
    public String getComments(@RequestParam("animal") String animalEnName) {
        return commentService.getHtmlComments(animalEnName);
    }

    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public void postComment(@RequestParam("animal") String animal, @RequestParam("content") String content, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");
        Timestamp dateSend = new Timestamp(System.currentTimeMillis());

        commentService.save(new CommentDTO(
                userId, content, dateSend, animal
        ));
    }
}
