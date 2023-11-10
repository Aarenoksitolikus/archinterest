package ru.itis.dao.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {
   private Long id;
   private Long authorId;
   private String title;
   private String content;
   private String address;
   private Double area;
   private Integer year;
   private LocalDateTime postedAt;
}
