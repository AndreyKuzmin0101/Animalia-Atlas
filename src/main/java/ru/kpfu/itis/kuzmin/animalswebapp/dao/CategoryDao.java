package ru.kpfu.itis.kuzmin.animalswebapp.dao;

import ru.kpfu.itis.kuzmin.animalswebapp.models.Category;

public interface CategoryDao {
    Category getByEnName(String enName);

}
