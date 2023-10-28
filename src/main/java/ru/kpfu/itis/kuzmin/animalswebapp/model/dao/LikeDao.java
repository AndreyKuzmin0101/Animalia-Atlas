package ru.kpfu.itis.kuzmin.animalswebapp.model.dao;

import java.util.List;

public interface LikeDao {
    boolean saveLikeAnimal(Integer userId, Integer animalId);
    boolean deleteLikeAnimal(Integer userId, Integer animalId);
    boolean findLikeAnimal(Integer userId, Integer animalId);
    List<Integer> getAnimalIdsByUserId(Integer userId);
}