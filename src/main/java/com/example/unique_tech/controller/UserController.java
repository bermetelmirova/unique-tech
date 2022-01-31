package com.example.unique_tech.controller;

import com.example.unique_tech.model.ResponseMessage;
import com.example.unique_tech.model.UserModel;
import com.example.unique_tech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseMessage<UserModel> createUser(@RequestBody UserModel userModel) {
        return new ResponseMessage<UserModel>()
                .prepareSuccessMessage(userService.save(userModel));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseMessage<UserModel> getById(@PathVariable Long id) {
        return new ResponseMessage<UserModel>()
                .prepareSuccessMessage(userService.findById(id));
    }

    @GetMapping("/get-all")
    public List<UserModel> getAll() {
        return userService.getAll();
    }

    @PutMapping()
    public ResponseMessage<UserModel> update(@RequestBody UserModel userModel) {
        return new ResponseMessage<UserModel>()
                .prepareSuccessMessage(userService.update(userModel));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseMessage<UserModel> deleteById(@PathVariable Long id) {
        return new ResponseMessage<UserModel>()
                .prepareSuccessMessage(userService.deleteById(id));
    }


}
