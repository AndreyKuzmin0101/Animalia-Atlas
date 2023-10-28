package ru.kpfu.itis.kuzmin.animalswebapp.model.dao;

import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Category;

public interface CategoryDao {
    Category getByEnName(String enName);

}
