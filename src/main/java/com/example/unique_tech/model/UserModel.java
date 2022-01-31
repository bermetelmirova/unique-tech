package com.example.unique_tech.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserModel {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String lastName;
    private String birthDate;
    private String address;
    private String description;
}
