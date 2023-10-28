package ru.kpfu.itis.kuzmin.animalswebapp.model.utils.rowmapperimpl;

import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Category;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category from(ResultSet resultSet, int rowNum) throws SQLException {
        return new Category(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3)
        );
    }
}
