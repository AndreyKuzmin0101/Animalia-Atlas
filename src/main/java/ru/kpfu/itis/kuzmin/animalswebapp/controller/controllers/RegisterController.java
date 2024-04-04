package ru.kpfu.itis.kuzmin.animalswebapp.controller.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.UsersServices;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final  UsersServices usersService;
    @GetMapping("/register")
    public String getRegisterForm(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName,
                         @RequestParam("age") String ageParam, @RequestParam("email") String email,
                         @RequestParam("login") String login, @RequestParam("password") String password,
                         Writer writer) throws IOException {
        Integer age = !ageParam.equals("") ? Integer.parseInt(ageParam): 0;

        String result = usersService.saveUser(new User(
                null, firstName, lastName, age, email, login, password, "https://res.cloudinary.com/debjgvnym/image/upload/bjgclwsmr3lkkpsjeebg.png")
        );

        if (result != null) {
            writer.append(result);
            return null;
        } else {
            return "redirect:/login";
        }
    }
}
