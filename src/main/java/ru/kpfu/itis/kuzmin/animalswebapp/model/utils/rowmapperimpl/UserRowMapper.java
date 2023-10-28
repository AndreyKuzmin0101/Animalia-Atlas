package ru.kpfu.itis.kuzmin.animalswebapp.model.utils.rowmapperimpl;


import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User from(ResultSet resultSet, int rowNum) throws SQLException {
        return new User(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8)
        );
    }
}
