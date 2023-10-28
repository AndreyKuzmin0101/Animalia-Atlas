package ru.kpfu.itis.kuzmin.animalswebapp.model.dao.impl;

import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.LikeDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.DatabaseConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeDaoJdbcImpl implements LikeDao {
    public static final String SQL_SAVE_LIKE_ANIMAL = "insert into like_animal values (?, ?)";
    public static final String SQL_DELETE_LIKE_ANIMAL = "delete from like_animal where user_id = ? and animal_id = ?";
    public static final String SQL_FIND_LIKE_ANIMAL = "select * from like_animal where user_id = ? and animal_id = ?";
    @Override
    public boolean saveLikeAnimal(Integer user_id, Integer animal_id) {
        return saveDeleteLike(user_id, animal_id, SQL_SAVE_LIKE_ANIMAL);
    }
    @Override
    public boolean deleteLikeAnimal(Integer user_id, Integer animal_id) {
        return saveDeleteLike(user_id, animal_id, SQL_DELETE_LIKE_ANIMAL);
    }

    @Override
    public boolean findLikeAnimal(Integer user_id, Integer animal_id) {
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection()
                .prepareStatement(SQL_FIND_LIKE_ANIMAL)) {
            int i = 1;
            statement.setInt(i++, user_id);
            statement.setInt(i++, animal_id);

            try (ResultSet resultSet = statement.executeQuery()){
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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