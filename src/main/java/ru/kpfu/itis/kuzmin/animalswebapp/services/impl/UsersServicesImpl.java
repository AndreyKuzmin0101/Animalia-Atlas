package ru.kpfu.itis.kuzmin.animalswebapp.services.impl;

import ru.kpfu.itis.kuzmin.animalswebapp.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.services.UsersServices;
import ru.kpfu.itis.kuzmin.animalswebapp.utils.PasswordUtil;
import ru.kpfu.itis.kuzmin.animalswebapp.utils.ValidatorUtil;

public class UsersServicesImpl implements UsersServices {
    @Override
    public String saveUser(User newUser, UsersDao usersDao) {

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
    public String updateUser(User oldUser, User updatedUser, UsersDao usersDao) {

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
    public void updateUserImage(User updatedUser, UsersDao usersDao) {
        usersDao.update(updatedUser);
    }

}
