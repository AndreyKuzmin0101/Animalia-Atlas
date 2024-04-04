package ru.kpfu.itis.kuzmin.animalswebapp.model.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.CategoryRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.SpecificAnimalRepo;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpecificAnimalRepoImpl implements SpecificAnimalRepo {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Animal> findByCategoryId(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            return category.getAnimals();
        }
        return new ArrayList<>();
    }
}
