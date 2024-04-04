package ru.kpfu.itis.kuzmin.animalswebapp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Optional<Animal> findByEnName(String name);

    Optional<Animal> findById(Integer id);

    List<Animal> findAll();

}
