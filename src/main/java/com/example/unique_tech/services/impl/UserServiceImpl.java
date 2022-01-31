package com.example.unique_tech.services.impl;

import com.example.unique_tech.converter.UserConvertor;
import com.example.unique_tech.entity.User;
import com.example.unique_tech.exception.ApiException;
import com.example.unique_tech.model.UserModel;
import com.example.unique_tech.repository.UserRepository;
import com.example.unique_tech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConvertor userConvertor;

    @Override
    public UserModel save(UserModel userModel) {
        User checkLogin = userRepository.findByLogin(userModel.getLogin()).orElse(null);
        if (checkLogin != null)
            throw new ApiException("Такой пользователь уже есть!", HttpStatus.BAD_REQUEST);
        User user = userConvertor.convertFromModel(userModel);
        return userConvertor.convertFromEntity(userRepository.save(user));
    }

    @Override
    public UserModel findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException("Пользователь не найден", HttpStatus.BAD_REQUEST));
        return userConvertor.convertFromEntity(user);
    }

    @Override
    public UserModel deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException("Пользователь не найден", HttpStatus.BAD_REQUEST));
        userRepository.deleteById(id);
        return userConvertor.convertFromEntity(user);
    }

    @Override
    public UserModel update(UserModel userModel) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;

        User user = userRepository
                .findById(userModel.getId())
                .orElse(null);

        if (userModel.getLogin() != null)
            user.setLogin(userModel.getLogin());
        if (userModel.getPassword() != null)
            user.setPassword(userModel.getPassword());
        if (userModel.getName() != null)
            user.setName(userModel.getName());
        if (userModel.getLastName() != null)
            user.setLastName(userModel.getLastName());
        if (userModel.getBirthDate() != null)
            user.setBirthDate(LocalDate.parse(userModel.getBirthDate(), dateTimeFormatter).atStartOfDay());
        if (userModel.getAddress() != null)
            user.setAddress(userModel.getAddress());
        if (userModel.getDescription() != null)
            user.setDescription(user.getDescription());

        user = userRepository.save(user);

        return userConvertor.convertFromEntity(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    @Override
    public List<UserModel> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userConvertor::convertFromEntity)
                .collect(Collectors.toList());
    }
}
