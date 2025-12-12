package com.library.system.service;

import com.library.system.model.User;
import com.library.system.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        // We rely on the UserRepository method (which exists in your code)
        return userRepository.findByUsername(username);
    }
}