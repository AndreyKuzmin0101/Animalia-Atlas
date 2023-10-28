package ru.kpfu.itis.kuzmin.animalswebapp.model.dao;

import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;

import java.util.List;

public interface AnimalDao {
    Animal getByEnName(String name);
    Animal getById(Integer id);
    List<Animal> getByCategoryId(Integer id);

    List<Animal> getAll();
    void updateLikes(Integer animalId, Integer likes);
}
