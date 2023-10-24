package ru.kpfu.itis.kuzmin.animalswebapp.services.impl;

import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.models.Comment;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.CommentDao;
import ru.kpfu.itis.kuzmin.animalswebapp.services.CommentServices;

import java.util.List;

public class CommentServicesImpl implements CommentServices {
    @Override
    public List<Comment> getComments(String animalEnName, CommentDao commentDao, AnimalDao animalDao) {
        Animal animal = animalDao.getByEnName(animalEnName);
        return commentDao.findByAnimalId(animal.getId());
    }
}
