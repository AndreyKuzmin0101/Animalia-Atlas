package ru.kpfu.itis.kuzmin.animalswebapp.services;

import ru.kpfu.itis.kuzmin.animalswebapp.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.models.User;

public interface UsersServices {
    String saveUser(User newUser, UsersDao usersDao);
    String updateUser(User oldUser, User updatedUser, UsersDao usersDao);
    void updateUserImage(User updatedUser, UsersDao usersDao);
}
