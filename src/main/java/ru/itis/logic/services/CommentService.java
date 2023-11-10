package ru.itis.logic.services;

import ru.itis.dao.entities.News;
import ru.itis.dao.entities.NewsComment;
import ru.itis.dao.entities.Project;

import java.util.List;

public interface CommentService {
    List<NewsComment> getList(News news);

    List<NewsComment> getList(Project project);
}
