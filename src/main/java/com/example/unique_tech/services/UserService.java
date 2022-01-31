package com.example.unique_tech.services;

import com.example.unique_tech.entity.User;
import com.example.unique_tech.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel save(UserModel user);

    UserModel findById(Long id);

    UserModel deleteById(Long id);

    UserModel update(UserModel userModel);

    User findByLogin(String login);

    List<UserModel> getAll();
}
