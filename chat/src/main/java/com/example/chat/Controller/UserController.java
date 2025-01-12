package com.example.chat.Controller;

import com.example.chat.Entity.User;
import com.example.chat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/usernames")
    public List<String> getAllUserNames() {
        return userRepository.findAllBy()
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }
}
