package ru.kpfu.itis.kuzmin.animalswebapp.services;

import ru.kpfu.itis.kuzmin.animalswebapp.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.CommentDao;
import ru.kpfu.itis.kuzmin.animalswebapp.models.Comment;

import java.util.List;

public interface CommentServices {
    List<Comment> getComments(String animalEnName, CommentDao commentDao, AnimalDao animalDao);
}
