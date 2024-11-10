package com.ust.stock_userdetails.service;

import com.ust.stock_userdetails.model.UserCredentials;
import com.ust.stock_userdetails.client.Category;
import com.ust.stock_userdetails.client.FullResponse;
import com.ust.stock_userdetails.feign.CategoryClient;
import com.ust.stock_userdetails.repository.UserCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCredentialsService {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserCredentialsRepo userCredentialsRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CategoryClient categoryClient;

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
    public List<UserCredentials> getAllUsers(){
        return userCredentialsRepo.findAll();
    }

    public Optional<UserCredentials> getUserByUsername(String username){
        return userCredentialsRepo.findById(username);
    }

    public String deleteUserByUsername(String username){
        Optional<UserCredentials> user = userCredentialsRepo.findById(username);
        if(user.isPresent()){
            userCredentialsRepo.deleteById(username);
            return "User deleted";
        }
        else{
            return "Cannot find user";
        }
    }

    public UserCredentials updateUser(String username,UserCredentials user){
        Optional<UserCredentials> userOptional = userCredentialsRepo.findById(username);
        if(userOptional.isPresent()){
            UserCredentials existingUser = userOptional.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            existingUser.setCategoryId(user.getCategoryId());
            return userCredentialsRepo.save(existingUser);
        }
        return null;
    }

    public FullResponse getCategoryByUsername(String username) throws Exception{
        UserCredentials user = userCredentialsRepo.findById(username).orElseThrow(Exception::new);
       Category category = categoryClient.getCategoryById(user.getCategoryId()).orElseThrow(Exception::new);

       FullResponse response;
        response = new FullResponse(username,category);
        return response;
    }
}