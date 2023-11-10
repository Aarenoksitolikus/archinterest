package ru.itis.dao.repositories;

import ru.itis.dao.entities.Tag;
import ru.itis.dao.entities.User;

import java.util.List;

public interface TagRepository {
    List<Tag> findAll(User current);

    List<Tag> findAll();
}
