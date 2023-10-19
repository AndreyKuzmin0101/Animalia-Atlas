package ru.kpfu.itis.kuzmin.animalswebapp.services;

import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.models.Comment;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.CommentDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.impl.AnimalDaoJdbcImpl;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.impl.CommentDaoJdbcImpl;

import java.util.List;

public class CommentServices {
    public static List<Comment> getComments(String animalEnName) {
        CommentDao commentDao = new CommentDaoJdbcImpl();
        AnimalDao animalDao = new AnimalDaoJdbcImpl();
        Animal animal = animalDao.getByEnName(animalEnName);
        return commentDao.findByAnimalId(animal.getId());
    }
}
