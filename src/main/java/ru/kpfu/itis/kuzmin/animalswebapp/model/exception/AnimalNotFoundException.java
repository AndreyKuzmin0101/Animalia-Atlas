package ru.kpfu.itis.kuzmin.animalswebapp.model.exception;

public class AnimalNotFoundException extends ServiceNotFoundException {
    public AnimalNotFoundException(String name) {
        super("Animal with name = %s - not found".formatted(name));
    }
}
