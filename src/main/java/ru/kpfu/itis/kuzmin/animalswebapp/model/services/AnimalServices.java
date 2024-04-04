package ru.kpfu.itis.kuzmin.animalswebapp.model.services;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.AnimalDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;

import java.util.List;

public interface AnimalServices {
    List<Animal> getLikedByUserId(Integer id);

    AnimalDTO getByEnName(String animal);

    Animal getById(Integer animalId);

    List<Animal> getByCategoryId(Integer id);

    List<Animal> getAll();

    List<AnimalDTO> getByQueryAndCategory(String query, String categoryEnName);
}
