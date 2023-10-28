package ru.kpfu.itis.kuzmin.animalswebapp.model.dao;

public interface LikeDao {
    boolean saveLikeAnimal(Integer user_id, Integer animal_id);
    boolean deleteLikeAnimal(Integer user_id, Integer animal_id);
    boolean findLikeAnimal(Integer user_id, Integer animal_id);

}