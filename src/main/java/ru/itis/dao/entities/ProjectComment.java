package ru.itis.dao.entities.abs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectComment extends Comment {
    private Long projectId;
}
