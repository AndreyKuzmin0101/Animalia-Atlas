package ru.kpfu.itis.kuzmin.animalswebapp.model.dao;

import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;

import java.util.List;

public interface LikeRepository {
    boolean saveLikeAnimal(Integer userId, Integer animalId);
    boolean deleteLikeAnimal(Integer userId, Integer animalId);
    boolean findLikeAnimal(Integer userId, Integer animalId);
    List<Animal> findAnimalByUserId(Integer userId);

    Integer countByAnimalId(Integer animalId);
}