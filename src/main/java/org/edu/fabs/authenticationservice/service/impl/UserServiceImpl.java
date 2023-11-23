package org.edu.fabs.authenticationservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.edu.fabs.authenticationservice.domain.User;
import org.edu.fabs.authenticationservice.repository.UserRepository;
import org.edu.fabs.authenticationservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> allUsers() {
        List<User> users = new ArrayList<>(userRepository.findAll());
        return users;
    }

}
