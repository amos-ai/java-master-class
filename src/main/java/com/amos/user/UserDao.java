package com.amos.user;

import java.util.UUID;

public class UserDao {
    // ARRAY LIVES HERE
    // search users
    // give me user by ID

    private User[] users;
    //private int count;

    public UserDao(User[] users) {
        this.users = users;
        //this.count = users.length;
    }

    public User findUserById(UUID uuid) {
        // scann through all user
        // if user name exist and id is equal
        // return user

        for (User user : users) {
            if (user != null && user.getUuid().equals(uuid)) {
                return user;
            }
        }
        return null;
    }

    public User[] getAllUsers() {
        return users;
    }
}
