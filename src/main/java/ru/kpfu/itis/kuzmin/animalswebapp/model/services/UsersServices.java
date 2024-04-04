package ru.kpfu.itis.kuzmin.animalswebapp.model.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.UserDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;

import java.io.IOException;
import java.util.List;

public interface UsersServices {
    String saveUser(User newUser);
    String updateUser(User oldUser, User updatedUser);
    List<UserDTO> getAll();
    User getById(Integer id);
    UserDTO getByLogin(String login);
    void updateImage(MultipartFile image, String login) throws IOException;
}
