package ru.itis.dao.repositories;

import ru.itis.dao.entities.Project;
import ru.itis.dao.entities.User;

import java.util.List;

public interface ProjectsRepository {
    List<Project> findAllByUser(User current);

    List<Project> findAll(User current);

    void create(Project project);

    Project get(Long projectId);
}
