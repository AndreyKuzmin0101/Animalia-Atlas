package ru.kpfu.itis.kuzmin.animalswebapp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByEnName(String enName);

}
