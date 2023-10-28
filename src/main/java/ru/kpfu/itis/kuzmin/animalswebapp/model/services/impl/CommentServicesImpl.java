package ru.kpfu.itis.kuzmin.animalswebapp.model.services.impl;

import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Comment;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.CommentDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.CommentServices;

import java.util.List;

public class CommentServicesImpl implements CommentServices {
    CommentDao commentDao;
    AnimalDao animalDao;
    public CommentServicesImpl(CommentDao commentDao, AnimalDao animalDao) {
        this.commentDao = commentDao;
        this.animalDao = animalDao;
    }
    @Override
    public List<Comment> getComments(String animalEnName) {
        Animal animal = animalDao.getByEnName(animalEnName);
        return commentDao.findByAnimalId(animal.getId());
    }
}
