package com.ust.stock_userdetails.Service;

import com.ust.stock_userdetails.Model.UserCredentials;
import com.ust.stock_userdetails.Repository.UserCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserCredentialsRepo userCredentialsRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserCredentials register(UserCredentials user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userCredentialsRepo.save(user);
    }

    public String generateToken(String name) {
        return jwtService.generateToken(name);
    }

    public boolean verifyToken(String token) {
        jwtService.validateToken(token);
        return true;
    }
}