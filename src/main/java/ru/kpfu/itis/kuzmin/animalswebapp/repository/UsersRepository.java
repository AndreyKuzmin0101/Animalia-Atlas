package ru.kpfu.itis.kuzmin.animalswebapp.repository;

import ru.kpfu.itis.kuzmin.animalswebapp.models.User;

public interface UsersRepository extends CrudRepository<User> {
    User getByLogin(String login);
    User getByEmail(String email);
    User getById(Integer id);
}
