package ru.kpfu.itis.kuzmin.animalswebapp.dao;

import ru.kpfu.itis.kuzmin.animalswebapp.models.User;

import java.util.List;

public interface UsersDao {
    void save(User model);
    void update(User model);
    User getByLogin(String login);
    User getByEmail(String email);
    User getById(Integer id);
    List<User> getAll();
}
