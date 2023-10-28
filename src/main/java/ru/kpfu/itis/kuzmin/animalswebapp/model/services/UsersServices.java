package ru.kpfu.itis.kuzmin.animalswebapp.model.services;

import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.UserDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;

import java.util.List;

public interface UsersServices {
    String saveUser(User newUser);
    String updateUser(User oldUser, User updatedUser);
    void updateUserImage(User updatedUser);
    List<UserDTO> getAll();
    UserDTO getById(Integer id);
}
