package ru.kpfu.itis.kuzmin.animalswebapp.servlets;

import ru.kpfu.itis.kuzmin.animalswebapp.dao.AnimalDao;
import ru.kpfu.itis.kuzmin.animalswebapp.models.Comment;
import ru.kpfu.itis.kuzmin.animalswebapp.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.CommentDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.services.CommentServices;
import ru.kpfu.itis.kuzmin.animalswebapp.services.impl.CommentServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@WebServlet(name = "commentServlet", urlPatterns = "/comments")

//TODO: переделать формат ответа
public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String animalEnName = req.getParameter("animal");

        CommentServices commentServices = (CommentServices) req.getServletContext().getAttribute("commentServices");
        List<Comment> comments = commentServices.getComments(animalEnName,
                (CommentDao) req.getServletContext().getAttribute("commentDao"),
                (AnimalDao) req.getServletContext().getAttribute("animalDao")
        );

        UsersDao usersDao = (UsersDao) req.getServletContext().getAttribute("usersDao");

        StringBuilder response = new StringBuilder();
        for (Comment comment : comments) {
            User user = usersDao.getById(comment.getUserId());
            response.append(
                            "<div class=\"comment\">\n").append(
                            "    <img class=\"comment-avatar\" src=\"").append(user.getImage()).append("\" alt=\"Аватар Пользователя\">\n").append(
                            "        <div class=\"comment-content\">\n").append(
                            "            <div class=\"comment-user\">").append(user.getFirstName()).append(" ").append(user.getLastName()).append("</div>\n").append(
                            "            <div class=\"comment-date\">").append(comment.getDateSend().toString().substring(0, 19)).append("</div>\n").append(
                            "            <p class=\"comment-text\">").append(comment.getContent()).append("</p>\n").append(
                            "        </div>\n").append(
                            "</div>\n"
            );
        }
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.valueOf(req.getSession(false).getAttribute("id").toString());
        String content = req.getParameter("content");
        Integer animalId = Integer.valueOf(req.getSession(false).getAttribute("animal_id").toString());
        Timestamp dateSend = new Timestamp(System.currentTimeMillis());

        CommentDao commentDao = (CommentDao) req.getServletContext().getAttribute("commentDao");
        commentDao.save(new Comment(
                null, userId, content, dateSend, animalId
        ));
    }
}
