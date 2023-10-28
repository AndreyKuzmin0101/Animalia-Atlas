package ru.kpfu.itis.kuzmin.animalswebapp.model.services.impl;

import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.LikeDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.AnimalServices;

import java.util.ArrayList;
import java.util.List;

public class AnimalServicesImpl implements AnimalServices {
    private AnimalDao animalDao;
    private LikeDao likeDao;
    public AnimalServicesImpl(AnimalDao animalDao, LikeDao likeDao) {
        this.animalDao = animalDao;
        this.likeDao = likeDao;
    }

    @Override
    public List<Animal> getLikedByUserId(Integer id) {
        List<Animal> animals = new ArrayList<>();

        List<Integer> animalIds = likeDao.getAnimalIdsByUserId(id);

        for (Integer animalId : animalIds) {
            animals.add(animalDao.getById(animalId));
        }

        return animals;
    }
}
