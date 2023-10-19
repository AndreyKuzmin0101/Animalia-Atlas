package ru.kpfu.itis.kuzmin.animalswebapp.dao;

import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;

public interface AnimalDao {
    Animal getByEnName(String name);
}
