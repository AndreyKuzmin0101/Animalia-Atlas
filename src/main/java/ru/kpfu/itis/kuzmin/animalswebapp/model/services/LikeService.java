package ru.kpfu.itis.kuzmin.animalswebapp.model.services;

import org.springframework.stereotype.Service;

public interface LikeService {
    boolean findLikeAnimal(Integer userId, String animalEnName);

    void deleteLikeAnimal(Integer userId, Integer animalId);

    void saveLikeAnimal(Integer userId, Integer animalId);

    Integer getLikesByAnimalId(Integer id);

    Integer like(Integer userId, String animalEnName);
}
