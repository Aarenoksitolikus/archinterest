package ru.itis.dao.repositories.impl;

import ru.itis.dao.entities.Tag;
import ru.itis.dao.entities.User;
import ru.itis.dao.repositories.TagRepository;

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
