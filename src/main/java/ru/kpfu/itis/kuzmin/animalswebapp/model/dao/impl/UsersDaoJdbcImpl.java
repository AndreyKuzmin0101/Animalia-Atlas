package ru.kpfu.itis.kuzmin.animalswebapp.model.dao.impl;

import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.DatabaseConnectionUtil;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.RowMapper;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.rowmapperimpl.UserRowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoJdbcImpl implements UsersDao {
    public static final String SQL_SAVE = "insert into users (first_name, last_name, age, email, login, password, image)\n" +
            "values (?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_GET_BY_LOGIN = "select * from users where login = ?";
    public static final String SQL_GET_BY_EMAIL = "select * from users where email = ?";
    public static final String SQL_GET_BY_ID = "select * from users where id = ?";
    public static final String SQL_UPDATE = "update users set first_name = ?, last_name = ?, age = ?, " +
            "email = ?, login = ?, password = ?, image = ? " +
            "where id = ?";
    public static final String SQL_GET_ALL = "select * from users";


    @Override
    public void save(User model) {
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection()
                .prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            statement.setString(i++, model.getFirstName());
            statement.setString(i++, model.getLastName());
            statement.setInt(i++, model.getAge());
            statement.setString(i++, model.getEmail());
            statement.setString(i++, model.getLogin());
            statement.setString(i++, model.getPassword());
            statement.setString(i++, model.getImage());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("Cannot insert user");
            }

            try (ResultSet generatedIds = statement.getGeneratedKeys()){
                if (generatedIds.next()) {
                    model.setId(generatedIds.getInt("id"));
                } else {
                    throw new SQLException("Cannot retrieve id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User model) {
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection()
                .prepareStatement(SQL_UPDATE)) {
            int i = 1;
            statement.setString(i++, model.getFirstName());
            statement.setString(i++, model.getLastName());
            statement.setInt(i++, model.getAge());
            statement.setString(i++, model.getEmail());
            statement.setString(i++, model.getLogin());
            statement.setString(i++, model.getPassword());
            statement.setString(i++, model.getImage());
            statement.setInt(i++, model.getId());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("Cannot update user");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getByLogin(String login) {
        return getByUniqueStringField(SQL_GET_BY_LOGIN, login);
    }

    @Override
    public User getByEmail(String email) {
        return getByUniqueStringField(SQL_GET_BY_EMAIL, email);
    }

    @Override
    public User getById(Integer id) {
        User user = null;
        RowMapper<User> rowMapper = new UserRowMapper();
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection().prepareStatement(SQL_GET_BY_ID)){
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    user = rowMapper.from(resultSet, 1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    private User getByUniqueStringField(String SQL, String field) {
        User user = null;
        RowMapper<User> rowMapper = new UserRowMapper();
        try (PreparedStatement statement = DatabaseConnectionUtil.getConnection().prepareStatement(SQL)){
            statement.setString(1, field);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    user = rowMapper.from(resultSet, 1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        RowMapper<User> rowMapper = new UserRowMapper();

        try (Statement statement = DatabaseConnectionUtil.getConnection().createStatement()){
            try (ResultSet resultSet = statement.executeQuery(SQL_GET_ALL)){
                while (resultSet.next()) {
                    int i = 1;
                    users.add(rowMapper.from(resultSet, i++));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

}
