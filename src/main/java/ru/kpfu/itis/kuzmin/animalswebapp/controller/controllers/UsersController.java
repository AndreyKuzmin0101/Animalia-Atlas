package ru.kpfu.itis.kuzmin.animalswebapp.controller.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.UserDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.exception.UserNotFoundException;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.UsersServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsersController {
    private final UsersServices usersService;

    @GetMapping("/users")
    public String getUsers(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null) {
            User user = usersService.getById(Integer.valueOf(id));
            if (user == null) {
                throw new UserNotFoundException(Integer.valueOf(id));
            }
            UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getAge(),
                    user.getEmail(), user.getImage());
            model.addAttribute("user", userDTO);
            return "user_profile";
        } else {

            List<UserDTO> users = usersService.getAll();
            model.addAttribute("users", users);
            return "list_users";
        }
    }
}
