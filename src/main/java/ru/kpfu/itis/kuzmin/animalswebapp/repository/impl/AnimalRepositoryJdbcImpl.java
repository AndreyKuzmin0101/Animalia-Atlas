package ru.kpfu.itis.kuzmin.animalswebapp.repository.impl;

import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.repository.AnimalRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.utils.DatabaseConnectionUtil;
import ru.kpfu.itis.kuzmin.animalswebapp.utils.RowMapper;
import ru.kpfu.itis.kuzmin.animalswebapp.utils.rowmapperimpl.AnimalRowMapper;
import ru.kpfu.itis.kuzmin.animalswebapp.utils.rowmapperimpl.UserRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalRepositoryJdbcImpl implements AnimalRepository {
    public static final String SQL_GET_BY_NAME = "select * from animals where en_name = ?";
    @Override
    public Animal getByEnName(String enName) {
        Animal animal = null;
        RowMapper<Animal> rowMapper = new AnimalRowMapper();
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection().prepareStatement(SQL_GET_BY_NAME)){
            statement.setString(1, enName);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    animal = rowMapper.from(resultSet, 1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animal;
    }
}
