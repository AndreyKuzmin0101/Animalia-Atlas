package ru.kpfu.itis.kuzmin.animalswebapp.model.utils.rowmapperimpl;

import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Comment;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment from(ResultSet resultSet, int rowNum) throws SQLException {
        return new Comment(resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getTimestamp(4),
                resultSet.getInt(5)
        );
    }
}
