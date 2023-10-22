package ru.kpfu.itis.kuzmin.animalswebapp.utils;

import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class PasswordUtil {

    public static String encrypt(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] digest = messageDigest.digest();
            String hex = DatatypeConverter.printHexBinary(digest);
            return hex.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
