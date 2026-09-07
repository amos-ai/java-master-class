package com.amos.user;

import org.w3c.dom.ls.LSOutput;

import java.util.UUID;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {

        this.userDao = userDao;
    }

    // get user
    public User getUserId(UUID uuid) {

        return userDao.findUserById(uuid);
    }

    public User[] getAllUsers() {

        return userDao.getAllUsers();
    }


}
