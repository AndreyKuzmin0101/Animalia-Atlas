package ru.kpfu.itis.kuzmin.animalswebapp.model.dao.impl;

import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.CategoryDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Category;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.DatabaseConnectionUtil;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.RowMapper;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.rowmapperimpl.CategoryRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDaoJdbcImpl implements CategoryDao {

    public static final String SQL_GET_BY_EN_NAME = "select * from categories where en_name = ?";
    @Override
    public Category getByEnName(String enName) {
        Category category = null;
        RowMapper<Category> rowMapper = new CategoryRowMapper();
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection().prepareStatement(SQL_GET_BY_EN_NAME)){
            statement.setString(1, enName);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    category = rowMapper.from(resultSet, 1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }
}
