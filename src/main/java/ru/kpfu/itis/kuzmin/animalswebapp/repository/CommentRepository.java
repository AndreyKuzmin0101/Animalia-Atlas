package ru.kpfu.itis.kuzmin.animalswebapp.repository;

import ru.kpfu.itis.kuzmin.animalswebapp.models.Comment;

import java.util.List;

public interface CommentRepository {
    void save(Comment comment);
    List<Comment> findByAnimalId(Integer id);
}
