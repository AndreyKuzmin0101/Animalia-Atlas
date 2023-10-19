package ru.kpfu.itis.kuzmin.animalswebapp.services;

import ru.kpfu.itis.kuzmin.animalswebapp.models.User;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.UsersDao;
import ru.kpfu.itis.kuzmin.animalswebapp.dao.impl.UsersDaoJdbcImpl;

public class UserServices {

    public static String writeUser(User oldUser, User updatedUser) {
        UsersDao usersDao = new UsersDaoJdbcImpl();

        User userByLogin = usersDao.getByLogin(updatedUser.getLogin());
        User userByEmail = usersDao.getByEmail(updatedUser.getEmail());

        if (userByLogin != null) {
            if (!(oldUser != null && !updatedUser.getLogin().equals("") &&
                    oldUser.getLogin().equals(updatedUser.getLogin()))){
                return "A user with this username already exists.";
            } else {
                userByLogin = null;
            }
        }
        if (userByEmail != null) {
            if (!(oldUser != null && !updatedUser.getEmail().equals("") &&
                    oldUser.getEmail().equals(updatedUser.getEmail()))){
                return "This email is already occupied.";
            } else {
                userByEmail = null;
            }
        }
        if (userByLogin == null && userByEmail == null) {
            if (oldUser != null) {
                if ("".equals(updatedUser.getFirstName())) updatedUser.setFirstName(oldUser.getFirstName());
                if ("".equals(updatedUser.getLastName())) updatedUser.setLastName(oldUser.getLastName());
                if (updatedUser.getAge() == null) updatedUser.setAge(oldUser.getAge());
                if ("".equals(updatedUser.getEmail())) updatedUser.setEmail(oldUser.getEmail());
                if ("".equals(updatedUser.getLogin())) updatedUser.setLogin(oldUser.getLogin());
                if ("".equals(updatedUser.getPassword())) updatedUser.setPassword(oldUser.getPassword());
                usersDao.update(updatedUser);
            }
            else {
                usersDao.save(updatedUser);
            }
        }
        return null;
    }

}
