package ru.kpfu.itis.kuzmin.animalswebapp.model.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.AnimalRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.CategoryRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.LikeRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.SpecificAnimalRepo;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.AnimalDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.exception.AnimalNotFoundException;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Category;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.AnimalServices;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.LikeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalServicesImpl implements AnimalServices {
    private final AnimalRepository animalRepository;
    private final LikeRepository likeRepository;
    private final SpecificAnimalRepo specificAnimalRepo;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Animal> getLikedByUserId(Integer id) {
        return likeRepository.findAnimalByUserId(id);
    }

    @Override
    public AnimalDTO getByEnName(String animalName) {
        Optional<Animal> optional = animalRepository.findByEnName(animalName);
        if (optional.isPresent()) {
            Animal animal = optional.get();
            return new AnimalDTO(animal.getName(), animal.getDescription(), animal.getImage(), animal.getEnName(),
                    likeRepository.countByAnimalId(animal.getId()));
        }
        throw new AnimalNotFoundException(animalName);
    }

    @Override
    public Animal getById(Integer animalId) {
        Optional<Animal> optionalAnimal = animalRepository.findById(animalId);
        return optionalAnimal.orElse(null);
    }

    @Override
    public List<Animal> getByCategoryId(Integer id) {
        return specificAnimalRepo.findByCategoryId(id);
    }

    @Override
    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    @Override
    public List<AnimalDTO> getByQueryAndCategory(String query, String categoryEnName) {
        List<Animal> animals = new ArrayList<>();

        List<Animal> animalsByCategory;
        if (categoryEnName != null && !categoryEnName.equals("")) {
            Category category = categoryRepository.findByEnName(categoryEnName);
            animalsByCategory = getByCategoryId(category.getId());
        } else {
            animalsByCategory = getAll();
        }

        if (query != null && !query.equals("")) {
            for (Animal animal: animalsByCategory) {
                if (animal.getName().toLowerCase().contains(query.toLowerCase())) {
                    animals.add(animal);
                }
            }
        } else {
            animals = animalsByCategory;
        }

        return animals.stream().map(animal -> new AnimalDTO(animal.getName(),
                animal.getDescription(), animal.getImage(), animal.getEnName(), null)).toList();
    }
}
