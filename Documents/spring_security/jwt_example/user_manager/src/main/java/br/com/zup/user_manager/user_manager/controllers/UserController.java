package br.com.zup.user_manager.user_manager.controllers;

import br.com.zup.user_manager.user_manager.UserService;
import br.com.zup.user_manager.user_manager.dtos.UserLoginDTO;
import br.com.zup.user_manager.user_manager.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserModel registerUser(@RequestBody UserModel user){
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserLoginDTO userLoginDTO){
        return userService.login(userLoginDTO);
    }
}
