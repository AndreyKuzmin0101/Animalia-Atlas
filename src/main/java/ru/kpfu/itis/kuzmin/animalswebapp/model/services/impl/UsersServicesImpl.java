package ru.kpfu.itis.kuzmin.animalswebapp.model.services.impl;

import ru.kpfu.itis.kuzmin.animalswebapp.model.dto.UserDTO;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.model.services.UsersServices;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.PasswordUtil;
import ru.kpfu.itis.kuzmin.animalswebapp.model.utils.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

public class UsersServicesImpl implements UsersServices {
    private UsersDao usersDao;
    public UsersServicesImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public String saveUser(User newUser) {

        User userByLogin = usersDao.getByLogin(newUser.getLogin());
        User userByEmail = usersDao.getByEmail(newUser.getEmail());


        if (!ValidatorUtil.validateEmail(newUser.getEmail())) {
            return "Email не прошёл верификацию.";
        }
        if(!ValidatorUtil.validatePassword(newUser.getPassword())) {
            return "Пароль не удовлетворяет требованиям.";
        }
        String safePassword = PasswordUtil.encrypt(newUser.getPassword());
        newUser.setPassword(safePassword);

        if (userByLogin != null) {
            return "Пользователь с таким логином уже существует";
        }

        if (userByEmail != null) {
            return "Данный email уже используется.";
        }

        usersDao.save(newUser);
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
            User userByLogin = usersDao.getByLogin(updatedUser.getLogin());
            if (userByLogin != null) {
                return "Пользователь с таким логином уже существует";
            }
        }

        if (!oldUser.getEmail().equals(updatedUser.getEmail())) {
            User userByEmail = usersDao.getByEmail(updatedUser.getEmail());
            if (!ValidatorUtil.validateEmail(updatedUser.getEmail())) {
                return "Email не прошёл верификацию.";
            }
            if (userByEmail != null) {
                return "Данный email уже используется.";
            }
        }

        if (!oldUser.getPassword().equals(updatedUser.getPassword())) {
            if(!ValidatorUtil.validatePassword(updatedUser.getPassword())) {
                return "Пароль не удовлетворяет требованиям.";
            }
            String safePassword = PasswordUtil.encrypt(updatedUser.getPassword());
            updatedUser.setPassword(safePassword);
        }

        usersDao.update(updatedUser);
        return null;

    }
    @Override
    public void updateUserImage(User updatedUser) {
        usersDao.update(updatedUser);
    }

    public List<UserDTO> getAll() {
        List<User> users = usersDao.getAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(new UserDTO(
                    user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getAge(), user.getEmail(), user.getImage()
            ));
        }

        return usersDTO;
    }

    @Override
    public UserDTO getById(Integer id) {
        User user = usersDao.getById(id);
        if (user != null) {
            return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getAge(), user.getEmail(), user.getImage());
        }
        return null;
    }

}
