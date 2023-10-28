package ru.kpfu.itis.kuzmin.animalswebapp.model.dao.impl;

import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.LikeDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.DatabaseConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeDaoJdbcImpl implements LikeDao {
    public static final String SQL_SAVE_LIKE_ANIMAL = "insert into like_animal values (?, ?)";
    public static final String SQL_DELETE_LIKE_ANIMAL = "delete from like_animal where user_id = ? and animal_id = ?";
    public static final String SQL_FIND_LIKE_ANIMAL = "select * from like_animal where user_id = ? and animal_id = ?";
    public static final String SQL_FIND_ANIMAL_BY_USER_ID = "select * from like_animal where user_id = ?";

    @Override
    public boolean saveLikeAnimal(Integer userId, Integer animalId) {
        return saveDeleteLike(userId, animalId, SQL_SAVE_LIKE_ANIMAL);
    }
    @Override
    public boolean deleteLikeAnimal(Integer userId, Integer animalId) {
        return saveDeleteLike(userId, animalId, SQL_DELETE_LIKE_ANIMAL);
    }

    @Override
    public boolean findLikeAnimal(Integer userId, Integer animalId) {
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection()
                .prepareStatement(SQL_FIND_LIKE_ANIMAL)) {
            int i = 1;
            statement.setInt(i++, userId);
            statement.setInt(i++, animalId);

            try (ResultSet resultSet = statement.executeQuery()){
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getAnimalIdsByUserId(Integer userId) {
        List<Integer> list = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection()
                .prepareStatement(SQL_FIND_ANIMAL_BY_USER_ID)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    list.add(resultSet.getInt(2));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    private boolean saveDeleteLike(Integer user_id, Integer entity_id, String SQL) {
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection()
                .prepareStatement(SQL)) {
            int i = 1;
            statement.setInt(i++, user_id);
            statement.setInt(i++, entity_id);

            if (statement.executeUpdate() != 1) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}