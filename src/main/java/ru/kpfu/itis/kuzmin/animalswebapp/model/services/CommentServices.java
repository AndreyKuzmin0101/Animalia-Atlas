package ru.kpfu.itis.kuzmin.animalswebapp.model.services;

import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Comment;

import java.util.List;

public interface CommentServices {
    List<Comment> getComments(String animalEnName);
}
