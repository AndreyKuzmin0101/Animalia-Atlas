package ru.kpfu.itis.kuzmin.animalswebapp.model.dao;

import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;

import java.util.List;

public interface SpecificAnimalRepo {
    List<Animal> findByCategoryId(Integer id);
}
