package com.library.system.service;

import com.library.system.model.User;

public interface UserService {
    // Finds the User entity by their username
    User findByUsername(String username);
}