package ru.kpfu.itis.kuzmin.animalswebapp.model.services;

import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;

import java.util.List;

public interface AnimalServices {
    List<Animal> getLikedByUserId(Integer id);
}
