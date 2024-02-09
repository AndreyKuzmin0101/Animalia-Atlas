package ru.kpfu.itis.kuzmin.animalswebapp.controller.servlets;

import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Comment;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.CommentDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.CommentServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@WebServlet(name = "commentServlet", urlPatterns = "/comments")

public class CommentServlet extends HttpServlet {
    private CommentServices commentServices;
    private UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        commentServices = (CommentServices) getServletContext().getAttribute("commentServices");
        usersDao = (UsersDao) getServletContext().getAttribute("usersDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String animalEnName = req.getParameter("animal");
        List<Comment> comments = commentServices.getComments(animalEnName);

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
