package ru.itis.dao.repositories;

import ru.itis.dao.entities.News;
import ru.itis.dao.entities.NewsComment;
import ru.itis.dao.entities.Project;
import ru.itis.dao.entities.abs.Comment;

import java.util.List;

public interface CommentRepository {
    List<NewsComment> getList(News news);
    List<NewsComment> getList(Project project);
}
