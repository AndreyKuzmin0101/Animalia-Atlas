package ru.kpfu.itis.kuzmin.animalswebapp.model.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.AnimalRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.LikeRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.UsersRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Transactional
public class LikeRepositoryImpl implements LikeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final UsersRepository userRepository;
    private final AnimalRepository animalRepository;

    @Override
    public boolean saveLikeAnimal(Integer userId, Integer animalId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Animal> optionalAnimal = animalRepository.findById(animalId);
        if (optionalUser.isPresent() && optionalAnimal.isPresent()) {
            User user = optionalUser.get();
            Animal animal = optionalAnimal.get();
            user.getAnimals().add(animal);
            entityManager.merge(animal);
            entityManager.flush();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteLikeAnimal(Integer userId, Integer animalId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Animal> optionalAnimal = animalRepository.findById(animalId);
        if (optionalUser.isPresent() && optionalAnimal.isPresent()) {
            User user = optionalUser.get();
            Animal animal = optionalAnimal.get();
            user.getAnimals().remove(animal);
            entityManager.merge(user);
            entityManager.flush();
            return true;
        }
        return false;
    }

    @Override
    public boolean findLikeAnimal(Integer userId, Integer animalId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Animal> optionalAnimal = animalRepository.findById(animalId);
        if (optionalUser.isPresent() && optionalAnimal.isPresent()) {
            User user = optionalUser.get();
            List<Integer> ids = user.getAnimals().stream().map(a -> a.getId()).collect(Collectors.toList());
            return ids.contains(animalId);
        }
        return false;
    }

    @Override
    public List<Animal> findAnimalByUserId(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getAnimals();
        }
        return new ArrayList<>();
    }

    @Override
    public Integer countByAnimalId(Integer animalId) {
        Query query = entityManager.createQuery("select count(u) from Animal a left join a.users u where a.id = :animalId");
        query.setParameter("animalId", animalId);
        return ((Long) query.getSingleResult()).intValue();
    }
}
