package ru.itis.dao.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class News {
    private Long id;
    private Long authorId;
    private String title;
    private String content;
    private String coverPath;
    private LocalDateTime createdAt;
}
