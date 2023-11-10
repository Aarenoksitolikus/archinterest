package ru.itis.dao.repositories.impl;

import ru.itis.dao.entities.Project;
import ru.itis.dao.entities.User;
import ru.itis.dao.repositories.ProjectsRepository;

import java.util.List;

public class ProjectsRepositoryImpl implements ProjectsRepository {
    @Override
    public List<Project> findAllByUser(User current) {
        return null;
    }

    @Override
    public List<Project> findAll(User current) {
        return null;
    }

    @Override
    public void create(Project project) {

    }

    @Override
    public Project get(Long projectId) {
        return null;
    }
}
