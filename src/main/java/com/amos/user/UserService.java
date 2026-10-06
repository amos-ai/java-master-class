package com.amos.user;

import java.util.UUID;

public class UserService {
    private final UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }


    public User getUserId(UUID uuid) {

        return userDao.findUserById(uuid);
    }

    public User[] getAllUsers() {

        return userDao.getUsers();
    }

}
