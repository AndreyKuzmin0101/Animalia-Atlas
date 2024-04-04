package ru.kpfu.itis.kuzmin.animalswebapp.model.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
