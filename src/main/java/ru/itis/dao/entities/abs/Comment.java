package ru.itis.dao.entities.abs;

import java.time.LocalDateTime;

public abstract class Comment {
    private Long id;
    private Long authorId;
    private String authorUsername;
    private String content;
    private Integer likes;
    private Integer dislikes;
    private LocalDateTime createdAt;
}
