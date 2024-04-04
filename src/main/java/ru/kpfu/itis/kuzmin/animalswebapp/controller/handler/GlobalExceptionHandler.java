package ru.kpfu.itis.kuzmin.animalswebapp.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.ExceptionDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.exception.ServiceNotFoundException;
import ru.kpfu.itis.kuzmin.animalswebapp.model.exception.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(ServiceNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "404";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final String onAllExceptions(HttpServletRequest request, Exception exception, Model model) {
        model.addAttribute("exception", new ExceptionDTO(request.getRequestURI(),
                500, exception.getMessage()));
        return "exception";
    }

}
