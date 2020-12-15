package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.User;
import com.eon.restaurant.eonsnack.server.payload.request.PreferencesRequest;
import com.eon.restaurant.eonsnack.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findByUsername(String userName) {

        User result = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));

        return result;
    }
}
