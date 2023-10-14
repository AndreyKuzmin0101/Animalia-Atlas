package ru.kpfu.itis.kuzmin.animalswebapp.utils.rowmapperimpl;

import ru.kpfu.itis.kuzmin.animalswebapp.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.utils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalRowMapper implements RowMapper<Animal> {

    @Override
    public Animal from(ResultSet resultSet, int rowNum) throws SQLException {
        return new Animal(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5)
        );
    }
}
