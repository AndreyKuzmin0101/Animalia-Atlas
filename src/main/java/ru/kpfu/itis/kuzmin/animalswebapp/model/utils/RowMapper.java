package ru.kpfu.itis.kuzmin.animalswebapp.model.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper <E>{
    E from(ResultSet resultSet, int rowNum) throws SQLException;
}
