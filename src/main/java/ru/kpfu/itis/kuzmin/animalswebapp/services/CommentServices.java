package ru.kpfu.itis.kuzmin.animalswebapp.services;

import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.models.Comment;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.CommentDao;
import java.util.List;

public class CommentServices {
    public static List<Comment> getComments(String animalEnName, CommentDao commentDao, AnimalDao animalDao) {
        System.out.println(animalEnName);
        System.out.println(1);
        Animal animal = animalDao.getByEnName(animalEnName);
        return commentDao.findByAnimalId(animal.getId());
    }
}
