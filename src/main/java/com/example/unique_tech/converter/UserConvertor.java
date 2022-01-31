package com.example.unique_tech.converter;

import com.example.unique_tech.entity.User;
import com.example.unique_tech.model.UserModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UserConvertor extends BaseConvertor<UserModel, User>{
    public UserConvertor() {
        super(UserConvertor::convertToEntity, UserConvertor::convertToModel);
    }

    private static UserModel convertToModel(User user) {
        if (user == null) return null;
        return UserModel.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .name(user.getName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate().toString())
                .address(user.getAddress())
                .description(user.getDescription())
                .build();
    }

    private static User convertToEntity(UserModel userModel) {
        if (userModel == null) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;
        return User.builder()
                .login(userModel.getLogin())
                .password(userModel.getPassword())
                .name(userModel.getName())
                .lastName(userModel.getLastName())
                .birthDate(LocalDate.parse(userModel.getBirthDate(), dateTimeFormatter).atStartOfDay())
                .address(userModel.getAddress())
                .description(userModel.getDescription())
                .build();
    }
}

