package ru.itis.dao.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime registeredAt;
    private String name;
    private String patronymic;
    private String lastname;
    private String about;
}
