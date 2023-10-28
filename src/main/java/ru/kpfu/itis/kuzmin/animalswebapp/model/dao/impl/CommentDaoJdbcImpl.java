package ru.kpfu.itis.kuzmin.animalswebapp.model.dao.impl;

import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Comment;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.CommentDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.DatabaseConnectionUtil;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.RowMapper;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.rowmapperimpl.CommentRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoJdbcImpl implements CommentDao {
    public static final String SQL_SAVE = "insert into comments(user_id, content, date_send, animal_id)\n" +
            "values (?, ?, ?, ?)";
    public static final String SQL_GET_BY_ANIMAL_ID_DESC = "select * from comments where animal_id = ? order by date_send desc";
    @Override
    public void save(Comment comment) {
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection()
                .prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            statement.setInt(i++, comment.getUserId());
            statement.setString(i++, comment.getContent());
            statement.setTimestamp(i++, comment.getDateSend());
            statement.setInt(i++, comment.getAnimalId());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("Cannot insert comment");
            }

            try (ResultSet generatedIds = statement.getGeneratedKeys()){
                if (generatedIds.next()) {
                    comment.setId(generatedIds.getInt("id"));
                } else {
                    throw new SQLException("Cannot retrieve id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findByAnimalId(Integer id) {
        ArrayList<Comment> comments = new ArrayList<>();
        RowMapper<Comment> rowMapper = new CommentRowMapper();
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection().prepareStatement(SQL_GET_BY_ANIMAL_ID_DESC)){
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                int i = 1;
                while (resultSet.next()) {
                    comments.add(rowMapper.from(resultSet, i++));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }
}
