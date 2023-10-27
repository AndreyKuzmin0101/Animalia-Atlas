package ru.kpfu.itis.kuzmin.animalswebapp.services;

import ru.kpfu.itis.kuzmin.animalswebapp.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dto.UserDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.models.User;

import java.util.List;

public interface UsersServices {
    String saveUser(User newUser);
    String updateUser(User oldUser, User updatedUser);
    void updateUserImage(User updatedUser);
    List<UserDTO> getAll();
    UserDTO getById(Integer id);
}
