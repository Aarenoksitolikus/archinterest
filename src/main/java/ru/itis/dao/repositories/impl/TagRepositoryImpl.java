package ru.itis.dao.repositories;

import ru.itis.dao.entities.Tag;
import ru.itis.dao.entities.User;

import java.util.List;

public class TagRepositoryImpl implements TagRepository {
    @Override
    public List<Tag> findAll(User current) {
        return null;
    }

    @Override
    public List<Tag> findAll() {
        return null;
    }
}
