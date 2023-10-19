package ru.kpfu.itis.kuzmin.animalswebapp.dao;

import ru.kpfu.itis.kuzmin.animalswebapp.models.Comment;

import java.util.List;

public interface CommentDao {
    void save(Comment comment);
    List<Comment> findByAnimalId(Integer id);
}
