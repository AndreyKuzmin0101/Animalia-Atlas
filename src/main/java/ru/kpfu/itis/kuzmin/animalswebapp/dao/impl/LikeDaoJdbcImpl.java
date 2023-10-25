package ru.kpfu.itis.kuzmin.animalswebapp.dao.impl;

import ru.kpfu.itis.kuzmin.animalswebapp.dao.LikeDao;
import ru.kpfu.itis.kuzmin.animalswebapp.utils.DatabaseConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LikeDaoJdbcImpl implements LikeDao {
    public static final String SQL_SAVE_LIKE_ANIMAL = "insert into like_animal values (?, ?)";
    public static final String SQL_DELETE_LIKE_ANIMAL = "delete from like_animal where user_id = ? and animal_id = ?";
    @Override
    public boolean saveLikeAnimal(Integer user_id, Integer animal_id) {
        return saveDeleteLike(user_id, animal_id, SQL_SAVE_LIKE_ANIMAL);
    }
    @Override
    public boolean deleteLikeAnimal(Integer user_id, Integer animal_id) {
        return saveDeleteLike(user_id, animal_id, SQL_DELETE_LIKE_ANIMAL);
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
