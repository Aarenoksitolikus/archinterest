package ru.itis.dao.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    private Long id;
    private Long authorId;
    private String authorUsername;
    private String content;
    private Integer likes;
    private Integer dislikes;
    private LocalDateTime createdAt;
}
