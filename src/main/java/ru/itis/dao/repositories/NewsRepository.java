package ru.itis.dao.repositories;

import ru.itis.dao.entities.News;
import ru.itis.dao.entities.User;

import java.time.LocalDateTime;
import java.util.List;

public interface NewsRepository {
    List<News> findAll();
    List<News> findAll(User current);
    List<News> getAllBetween(LocalDateTime from, LocalDateTime to);
    List<News> getAllBetweenByTags(LocalDateTime startOfDay, LocalDateTime endOfDay, User currentUser);
    News get(Long id);
    void save(News news);
}
