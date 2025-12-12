package com.library.system;

import com.library.system.model.User;
import com.library.system.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LibraryManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

    /**
     * FIX: This Bean runs code once the application is started.
     * It ensures the 'admin' and 'user' accounts are saved to the database,
     * so they can be retrieved by the UserService during the borrow process.
     */
    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if users already exist to prevent duplicates on every run
            if (userRepository.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123")); // Password must be encoded
                admin.setRole("ADMIN");
                admin.setFullName("System Administrator");
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("user") == null) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123")); // Password must be encoded
                user.setRole("USER");
                user.setFullName("Regular User");
                userRepository.save(user);
            }
        };
    }
}