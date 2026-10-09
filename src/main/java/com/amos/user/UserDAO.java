package com.amos.user;

import java.util.UUID;

public interface UserDAO {
    User [] getUsers();
    User findUserById(UUID userId);
}
