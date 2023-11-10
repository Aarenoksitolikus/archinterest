package ru.itis.logic.services.impl;

import ru.itis.dao.entities.Tag;
import ru.itis.dao.entities.User;
import ru.itis.dao.repositories.UserRepository;
import ru.itis.logic.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(Long id) {
        return userRepository.get(id);
    }

    @Override
    public User get(String username, String password) {
        return userRepository.get(username, password);
    }

    @Override
    public void create(String username, String email, String hash) {
        User user = User.builder()
                .username(username)
                .email(email)
                .password(hash)
                .build();

        userRepository.create(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void update(User profile, List<Tag> tags) {
        userRepository.update(profile, tags);
    }
}
