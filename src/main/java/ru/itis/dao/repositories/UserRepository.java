package ru.itis.dao.entities.repositories;

import ru.itis.dao.entities.User;

public interface UserRepository {
    User get(Long id);
}
