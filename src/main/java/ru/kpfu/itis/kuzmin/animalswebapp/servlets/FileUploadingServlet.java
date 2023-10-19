package ru.kpfu.itis.kuzmin.animalswebapp.servlets;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import ru.kpfu.itis.kuzmin.animalswebapp.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.impl.UsersDaoJdbcImpl;
import ru.kpfu.itis.kuzmin.animalswebapp.services.UserServices;
import ru.kpfu.itis.kuzmin.animalswebapp.utils.CloudinaryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@WebServlet(name = "fileUploadingServlet", urlPatterns= "/upload")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class FileUploadingServlet extends HttpServlet {
    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        String login = req.getSession(false).getAttribute("login").toString();
        long currentTimeMillis = System.currentTimeMillis();
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

        File file = new File(login + File.separator
                + currentTimeMillis + File.separator + filename);

        file.getParentFile().mkdirs();
        file.createNewFile();

        try (InputStream content = part.getInputStream()) {
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[content.available()];
                content.read(buffer);
                outputStream.write(buffer);
            }
        }

        filename = removeTypeFile(filename);
        String imagePath = login + "/" + currentTimeMillis + "/" + filename;

        cloudinary.uploader().upload(file, ObjectUtils.asMap("public_id", imagePath));

        UsersDao usersDao = new UsersDaoJdbcImpl();
        User user = usersDao.getByLogin(login);
        User updateUser = new User(user.getId(), user.getFirstName(), user.getLastName(),
                user.getAge(), user.getEmail(), user.getLogin(), user.getPassword(),
                "https://res.cloudinary.com/debjgvnym/image/upload/" + imagePath
        );

        UserServices.writeUser(user, updateUser);
        resp.sendRedirect("/profile");
    }

    private String removeTypeFile(String filename) {
        if (filename.endsWith(".png") || filename.endsWith(".jpg")) {
            filename = filename.substring(0, filename.length()-4);
        } else if (filename.endsWith(".jpeg")) {
            filename = filename.substring(0, filename.length()-5);
        }
        return filename;
    }
}
