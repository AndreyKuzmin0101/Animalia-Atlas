package ru.kpfu.itis.kuzmin.animalswebapp.controller.handler;

import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.ExceptionDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/handle")
public class ExceptionHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleException(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleException(req, resp);
    }

    private void handleException(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        Integer code = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String uri = (String) req.getAttribute("javax.servlet.error.request_uri");

        ExceptionDTO exceptionDTO = new ExceptionDTO((uri == null) ? "" : uri, code, null);
        if (code == 500) {
            exceptionDTO.setMessage(throwable.getMessage());
        }
        req.setAttribute("exception", exceptionDTO);
        req.getRequestDispatcher("/view/exception.ftl").forward(req, resp);
    }

}