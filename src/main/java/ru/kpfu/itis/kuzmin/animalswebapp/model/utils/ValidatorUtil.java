package ru.kpfu.itis.kuzmin.animalswebapp.model.utils;

import java.util.regex.Pattern;

public class ValidatorUtil {
    public static boolean validateEmail(String email) {
        return Pattern.compile("^\\S+@\\S+\\.\\S+$")
                .matcher(email)
                .find();

    }
    public static boolean validatePassword(String password) {
        return Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
                .matcher(password)
                .find();
    }
}
