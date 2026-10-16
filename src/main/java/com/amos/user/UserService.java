package com.amos.user;

import java.util.List;
import java.util.UUID;

public class UserService {
    private final UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }


    public User getUserId(UUID uuid) {

        return userDao.findUserById(uuid);
    }

    public List<User> getAllUsers() {

        return userDao.getUsers();
    }

}
