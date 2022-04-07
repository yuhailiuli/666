package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest registerRequest) {

        if(userRepository.getUserByName(registerRequest.name) != null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User already exists.");
        }

        User user = new User(registerRequest.name, registerRequest.password,
                registerRequest.email, registerRequest.phone);
        userRepository.save(user);

        return user;
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest) {

        User user = userRepository.getUserByName(loginRequest.name);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not exists.");
        }

        if (!loginRequest.password.equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password.");
        }

        return user;
    }

    @PostMapping("/editProfile")
    public String editProfile(@RequestBody ProfileEditRequest profileEditRequest) {
        User user = userRepository.getUserById(profileEditRequest.user_id);
        user.setName(profileEditRequest.username);
        user.setAddress(profileEditRequest.address);
        user.setGender(profileEditRequest.gender);
        user.setPhone(profileEditRequest.phone);
        user.setAge(profileEditRequest.age);
        user.setEmail(profileEditRequest.email);

        userRepository.save(user);
        return "Success";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.getUserById(changePasswordRequest.user_id);
        if(!changePasswordRequest.originPassword.equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password.");
        }

        user.setPassword(changePasswordRequest.newPassword);
        userRepository.save(user);

        return "Success";
    }
}
