package com.security.learn.controller;


import com.security.learn.model.CustomUser;
import com.security.learn.repository.CustomUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private CustomUserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody  CustomUser customUser){
        customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
        userRepo.save(customUser);
        return ResponseEntity.ok("Data is stored");
    }
}
