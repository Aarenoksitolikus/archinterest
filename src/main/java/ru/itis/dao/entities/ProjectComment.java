package ru.itis.dao.entities;

import lombok.*;
import ru.itis.dao.entities.abs.Comment;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectComment extends Comment {
    private Long projectId;
}
