package com.amos.user;

import java.util.UUID;


public class UserArrayDataAccessService implements UserDAO{

    private static final User[] users;

    static {
        users = new User[] {
                new User(UUID.fromString("d75751b4-d7fe-4e26-9d7f-38c880f9f7f1"), "James"),
                new User(UUID.fromString("2e0f7622-3d86-46ea-98e8-fd914d40c989"), "Jamila"),
                new User(UUID.fromString("97aade92-534f-4d66-a174-b160dde8e2ae"), "Dave"),
                new User(UUID.fromString("0960f770-e4ce-4c8a-baa7-54eabe310c30"), "Yasmin")

        };
    }


    @Override
    public User[] getUsers() {
        return users;
    }

    @Override
    public User findUserById(UUID userId) {
        for (User user : getUsers()) {
            if (user != null && user.getUuid().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}
