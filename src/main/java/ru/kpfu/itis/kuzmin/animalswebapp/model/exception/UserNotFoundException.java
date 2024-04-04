package ru.kpfu.itis.kuzmin.animalswebapp.model.exception;

public class UserNotFoundException extends ServiceNotFoundException {
    public UserNotFoundException(Integer id) {
        super("User with id = %s - not found".formatted(id));
    }

    public UserNotFoundException(String login) {
        super("User with login = %s - not found".formatted(login));
    }
}
