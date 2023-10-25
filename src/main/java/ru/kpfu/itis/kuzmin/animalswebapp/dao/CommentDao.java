package ru.kpfu.itis.kuzmin.animalswebapp.dao;

import ru.kpfu.itis.kuzmin.animalswebapp.models.Comment;

import java.util.List;

public interface CommentDao {
    Comment getById(Integer id);
    void save(Comment comment);
    List<Comment> findByAnimalId(Integer id);
    void updateLikes(Integer commentId, Integer likes);
}
