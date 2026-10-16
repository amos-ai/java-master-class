package com.amos.user;

import java.util.List;
import java.util.UUID;

public interface UserDAO {
    List<User>  getUsers();
    User findUserById(UUID userId);
}
