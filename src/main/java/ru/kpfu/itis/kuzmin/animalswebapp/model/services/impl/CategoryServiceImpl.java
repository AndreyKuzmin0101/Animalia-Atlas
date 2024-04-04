package ru.kpfu.itis.kuzmin.animalswebapp.model.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.CategoryRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Category;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public Category getByEnName(String categoryEnName) {
        return categoryRepository.findByEnName(categoryEnName);
    }
}
