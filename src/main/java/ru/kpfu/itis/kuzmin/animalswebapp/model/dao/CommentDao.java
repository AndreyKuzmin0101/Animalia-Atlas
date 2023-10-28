package ru.kpfu.itis.kuzmin.animalswebapp.model.dao;

import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Comment;

import java.util.List;

public interface CommentDao {
    void save(Comment comment);
    List<Comment> findByAnimalId(Integer id);
}
