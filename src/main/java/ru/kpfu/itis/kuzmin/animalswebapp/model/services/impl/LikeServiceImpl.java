package ru.kpfu.itis.kuzmin.animalswebapp.model.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.AnimalRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.LikeRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.exception.AnimalNotFoundException;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.LikeService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final AnimalRepository animalRepository;

    @Override
    public boolean findLikeAnimal(Integer userId, String animalEnName) {
        Optional<Animal> optionalAnimal = animalRepository.findByEnName(animalEnName);
        if (optionalAnimal.isPresent()) {
            Integer animalId = optionalAnimal.get().getId();
            return likeRepository.findLikeAnimal(userId, animalId);
        }
        throw new AnimalNotFoundException(animalEnName);

    }

    @Override
    public void deleteLikeAnimal(Integer userId, Integer animalId) {
        likeRepository.deleteLikeAnimal(userId, animalId);
    }

    @Override
    public void saveLikeAnimal(Integer userId, Integer animalId) {
        likeRepository.saveLikeAnimal(userId, animalId);
    }

    @Override
    public Integer getLikesByAnimalId(Integer id) {
        return likeRepository.countByAnimalId(id);
    }

    @Override
    public Integer like(Integer userId, String animalEnName) {
        Optional<Animal> optionalAnimal = animalRepository.findByEnName(animalEnName);
        if (optionalAnimal.isPresent()) {
            Integer animalId = optionalAnimal.get().getId();

            Integer likes = getLikesByAnimalId(animalId);
            if (findLikeAnimal(userId, animalEnName)){
                deleteLikeAnimal(userId, animalId);
                likes -= 1;
            } else {
                saveLikeAnimal(userId, animalId);
                likes += 1;
            }
            return likes;
        }
        throw new AnimalNotFoundException(animalEnName);
    }
}
