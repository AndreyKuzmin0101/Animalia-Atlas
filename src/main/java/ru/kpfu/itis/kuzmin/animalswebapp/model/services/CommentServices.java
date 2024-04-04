package ru.kpfu.itis.kuzmin.animalswebapp.model.services;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.CommentDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Comment;

import java.util.List;

public interface CommentServices {
    List<Comment> getComments(String animalEnName);

    void save(CommentDTO comment);

    String getHtmlComments(String animalEnName);
}
