package ru.kpfu.itis.kuzmin.animalswebapp.controller.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.UserDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.UsersServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

@Controller
@RequiredArgsConstructor
public class SettingsController {
    private final UsersServices usersService;
    private User user;

    @GetMapping("/settings")
    @PreAuthorize("hasRole('USER')")
    public String getForm(HttpSession session, Model model) {
        UserDTO userDTO = usersService.getByLogin((String) session.getAttribute("login"));

        model.addAttribute("user", userDTO);
        return "settings";

    }

    @PostMapping("/settings")
    @PreAuthorize("hasRole('USER')")
    public String updateSettings(@RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName,
                                 @RequestParam("age") String ageParam, @RequestParam("email") String email,
                                 @RequestParam("login") String login, @RequestParam("password") String password,
                                 Writer writer) throws IOException {
        Integer age = !ageParam.equals("") ? Integer.parseInt(ageParam): 0;

        String result = usersService.updateUser(user, new User(
                user.getId(), firstName, lastName, age, email, login, password, user.getImage())
        );
        if (result != null) {
            writer.append(result);
            return null;
        } else {
            return "redirect:/logout";
        }
    }
}
