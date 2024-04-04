package ru.kpfu.itis.kuzmin.animalswebapp.model.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.RoleRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.SpecificUserRepo;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.UserDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.exception.UserNotFoundException;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Role;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.UsersRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.UsersServices;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.CloudinaryUtil;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.ValidatorUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServicesImpl implements UsersServices {
    private final UsersRepository usersRepository;
    private final SpecificUserRepo specificUserRepo;
    private final RoleRepository roleRepository;


    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();
    private final PasswordEncoder passwordUtil;

    @Override
    public String saveUser(User newUser) {

        Optional<User> userByLogin = usersRepository.findByLogin(newUser.getLogin());
        Optional<User> userByEmail = usersRepository.findByEmail(newUser.getEmail());


        if (!ValidatorUtil.validateEmail(newUser.getEmail())) {
            return "Email не прошёл верификацию.";
        }
        if(!ValidatorUtil.validatePassword(newUser.getPassword())) {
            return "Пароль не удовлетворяет требованиям.";
        }
        String safePassword = passwordUtil.encode(newUser.getPassword());
        newUser.setPassword(safePassword);

        if (userByLogin.isPresent()) {
            return "Пользователь с таким логином уже существует";
        }

        if (userByEmail.isPresent()) {
            return "Данный email уже используется.";
        }

        newUser.setRoles(List.of(new Role(1, null, null)));
        usersRepository.save(newUser);
        return null;
    }
    @Override
    public String updateUser(User oldUser, User updatedUser) {

        if ("".equals(updatedUser.getFirstName())) updatedUser.setFirstName(oldUser.getFirstName());
        if ("".equals(updatedUser.getLastName())) updatedUser.setLastName(oldUser.getLastName());
        if (updatedUser.getAge() == 0) updatedUser.setAge(oldUser.getAge());
        if ("".equals(updatedUser.getEmail())) updatedUser.setEmail(oldUser.getEmail());
        if ("".equals(updatedUser.getLogin())) updatedUser.setLogin(oldUser.getLogin());
        if ("".equals(updatedUser.getPassword())) updatedUser.setPassword(oldUser.getPassword());


        if (!oldUser.getLogin().equals(updatedUser.getLogin())) {
            Optional<User> userByLogin = usersRepository.findByLogin(updatedUser.getLogin());
            if (userByLogin.isPresent()) {
                return "Пользователь с таким логином уже существует";
            }
        }

        if (!oldUser.getEmail().equals(updatedUser.getEmail())) {
            Optional<User> userByEmail = usersRepository.findByEmail(updatedUser.getEmail());
            if (!ValidatorUtil.validateEmail(updatedUser.getEmail())) {
                return "Email не прошёл верификацию.";
            }
            if (userByEmail.isPresent()) {
                return "Данный email уже используется.";
            }
        }

        if (!oldUser.getPassword().equals(updatedUser.getPassword())) {
            if(!ValidatorUtil.validatePassword(updatedUser.getPassword())) {
                return "Пароль не удовлетворяет требованиям.";
            }
            String safePassword = passwordUtil.encode(updatedUser.getPassword());
            updatedUser.setPassword(safePassword);
        }
        updatedUser.setRoles(roleRepository.findRolesByUserId(updatedUser.getId()));
        specificUserRepo.update(updatedUser);
        return null;

    }

    public List<UserDTO> getAll() {
        List<User> users = usersRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getAge(),
                    user.getEmail(), user.getImage()
            ));
        }

        return usersDTO;
    }

    @Override
    public User getById(Integer id) {
        Optional<User> optionalUser = usersRepository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public UserDTO getByLogin(String login) {
        Optional<User> optionalUser = usersRepository.findByLogin(login);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(),
                    user.getAge(), user.getEmail(), user.getImage());
        }
        throw new UserNotFoundException(login);
    }

    @Override
    public void updateImage(MultipartFile image, String login) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        String filename = image.getName();

        File file = new File(login + File.separator
                + currentTimeMillis + File.separator + filename);

        file.getParentFile().mkdirs();
        file.createNewFile();

        try (InputStream content = image.getInputStream()) {
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[content.available()];
                content.read(buffer);
                outputStream.write(buffer);
            }
        }

        String filenameForCloudinary = removeTypeFile(filename);
        String imagePath = login + "/" + currentTimeMillis + "/";

        cloudinary.uploader().upload(file, ObjectUtils.asMap("public_id", imagePath + filenameForCloudinary));

        Optional<User> optionalUser = usersRepository.findByLogin(login);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setImage("https://res.cloudinary.com/debjgvnym/image/upload/" + imagePath + filename);
            specificUserRepo.update(user);
        } else {
            throw new UserNotFoundException(login);
        }

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
