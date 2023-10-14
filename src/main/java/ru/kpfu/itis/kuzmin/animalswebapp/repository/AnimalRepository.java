package ru.kpfu.itis.kuzmin.animalswebapp.repository;

import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;

public interface AnimalRepository {
    Animal getByEnName(String name);
}
