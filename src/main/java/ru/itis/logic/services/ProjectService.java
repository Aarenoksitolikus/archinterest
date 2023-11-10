package ru.itis.logic.services;

import ru.itis.dao.entities.Project;
import ru.itis.dao.entities.User;

import java.util.List;

public interface ProjectService {
    void create(Project project);

    Project get(Long projectId);

    List<Project> getList(User current);

    List<Project> getInterested(User current);
}
