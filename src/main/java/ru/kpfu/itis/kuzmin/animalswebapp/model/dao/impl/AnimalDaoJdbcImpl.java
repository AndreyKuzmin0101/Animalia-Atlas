package ru.kpfu.itis.kuzmin.animalswebapp.model.dao.impl;

import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.DatabaseConnectionUtil;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.RowMapper;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.rowmapperimpl.AnimalRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnimalDaoJdbcImpl implements AnimalDao {
    public static final String SQL_GET_BY_NAME = "select * from animals where en_name = ?";
    public static final String SQL_GET_IDS_BY_CATEGORY_ID = "select * from animal_category where category_id = ?";
    public static final String SQL_GET_BY_ID = "select * from animals where id = ?";
    public static final String SQL_GET_ALL = "select * from animals";
    public static final String SQL_UPDATE_LIKES = "update animals set likes = ? where id = ?";

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

    @Override
    public Animal getById(Integer id) {
        Animal animal = null;
        RowMapper<Animal> rowMapper = new AnimalRowMapper();
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection().prepareStatement(SQL_GET_BY_ID)){
            statement.setInt(1, id);
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

    @Override
    public List<Animal> getByCategoryId(Integer id) {
        List<Integer> animalsId = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();

        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection().prepareStatement(SQL_GET_IDS_BY_CATEGORY_ID)){
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    animalsId.add(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RowMapper<Animal> rowMapper = new AnimalRowMapper();

        for (Integer animalId: animalsId) {
            try (PreparedStatement statement = DatabaseConnectionUtil.getConnection().prepareStatement(SQL_GET_BY_ID)){
                statement.setInt(1, animalId);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        animals.add(rowMapper.from(resultSet, 1));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return animals;
    }

    @Override
    public List<Animal> getAll() {
        List<Animal> animals = new ArrayList<>();
        RowMapper<Animal> rowMapper = new AnimalRowMapper();

        try (Statement statement = DatabaseConnectionUtil.getConnection().createStatement()){
            try (ResultSet resultSet = statement.executeQuery(SQL_GET_ALL)){
                while (resultSet.next()) {
                    int i = 1;
                    animals.add(rowMapper.from(resultSet, i++));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animals;
    }

    @Override
    public void updateLikes(Integer animalId, Integer likes) {
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection().prepareStatement(SQL_UPDATE_LIKES)) {
            int i = 1;
            statement.setInt(i++, likes);
            statement.setInt(i++, animalId);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("Cannot update animal");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
