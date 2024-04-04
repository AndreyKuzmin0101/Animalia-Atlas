package ru.kpfu.itis.kuzmin.animalswebapp.model.services;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Category;

public interface CategoryService {
    Category getByEnName(String categoryEnName);
}
