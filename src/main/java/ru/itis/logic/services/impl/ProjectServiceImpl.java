package ru.itis.logic.services.impl;

import ru.itis.dao.entities.Project;
import ru.itis.dao.entities.User;
import ru.itis.dao.repositories.ProjectsRepository;
import ru.itis.logic.services.ProjectService;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    private final ProjectsRepository projectsRepository;

    public ProjectServiceImpl(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    @Override
    public List<Project> getList(User current) {
        return projectsRepository.findAllByUser(current);
    }

    @Override
    public void create(Project project) {
        projectsRepository.create(project);
    }

    @Override
    public List<Project> getInterested(User current) {
        return projectsRepository.findAll(current);
    }

    @Override
    public Project get(Long projectId) {
        return projectsRepository.get(projectId);
    }
}
