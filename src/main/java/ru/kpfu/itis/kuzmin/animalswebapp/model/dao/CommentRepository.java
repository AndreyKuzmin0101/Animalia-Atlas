package ru.kpfu.itis.kuzmin.animalswebapp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment saveAndFlush(Comment comment);
    List<Comment> findByAnimalId(Integer id);
}
