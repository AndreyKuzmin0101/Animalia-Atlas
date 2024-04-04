package ru.kpfu.itis.kuzmin.animalswebapp.controller.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.UsersServices;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.CloudinaryUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@Controller
//@MultipartConfig(
//        maxFileSize = 5 * 1024 * 1024,
//        maxRequestSize = 10 * 1024 * 1024
//)
public class FileUploadingController {
    private final UsersServices usersService;

    public FileUploadingController(UsersServices usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/upload")
    @PreAuthorize("hasRole('USER')")
    protected String postImage(@RequestParam MultipartFile image, HttpSession session) throws IOException {
        String login = session.getAttribute("login").toString();
        usersService.updateImage(image, login);
        return "redirect:/profile";
    }

}
