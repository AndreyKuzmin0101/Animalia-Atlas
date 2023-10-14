package ru.kpfu.itis.kuzmin.animalswebapp.services;

import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.models.Comment;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.AnimalRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.CommentRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.impl.AnimalRepositoryJdbcImpl;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.impl.CommentRepositoryJdbcImpl;

import java.util.List;

public class CommentServices {
    public static List<Comment> getComments(String animalEnName) {
        CommentRepository commentRepository = new CommentRepositoryJdbcImpl();
        AnimalRepository animalRepository = new AnimalRepositoryJdbcImpl();
        Animal animal = animalRepository.getByEnName(animalEnName);
        return commentRepository.findByAnimalId(animal.getId());
    }
}
