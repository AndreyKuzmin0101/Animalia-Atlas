package ru.kpfu.itis.kuzmin.animalswebapp.model.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.UsersRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.CommentDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.exception.AnimalNotFoundException;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Animal;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Comment;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.AnimalRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.CommentRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.CommentServices;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServicesImpl implements CommentServices {
    private final CommentRepository commentRepository;
    private final AnimalRepository animalRepository;
    private final UsersRepository usersRepository;

    @Override
    public List<Comment> getComments(String animalEnName) {
        Optional<Animal> optional = animalRepository.findByEnName(animalEnName);
        if (optional.isPresent()) {
            return commentRepository.findByAnimalId(optional.get().getId());
        }
        throw new AnimalNotFoundException(animalEnName);
    }

    @Override
    public void save(CommentDTO comment) {
        Optional<Animal> optionalAnimal = animalRepository.findByEnName(comment.getAnimalEnName());
        optionalAnimal.ifPresent(animal -> commentRepository.save(
                new Comment(null, comment.getUserId(), comment.getContent(), comment.getDateSend(),
                        animal.getId())
        ));
    }

    @Override
    public String getHtmlComments(String animalEnName) {
        List<Comment> comments = getComments(animalEnName);
        StringBuilder response = new StringBuilder();
        for (Comment comment : comments) {
            Optional<User> optionalUser = usersRepository.findById(comment.getUserId());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
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
        }
        return response.toString();
    }
}
