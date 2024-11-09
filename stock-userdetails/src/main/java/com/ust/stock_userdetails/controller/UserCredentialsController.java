package com.ust.stock_userdetails.controller;


import com.ust.stock_userdetails.Model.UserCredentials;
import com.ust.stock_userdetails.client.FullResponse;
import com.ust.stock_userdetails.service.JwtService;
import com.ust.stock_userdetails.service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usercredentials")
public class UserCredentialsController {
    @Autowired
    JwtService jwtService;

    @Autowired
    private UserCredentialsService userCredentialsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public UserCredentials register(@RequestBody UserCredentials user) {
        return userCredentialsService.register(user);
    }

    @GetMapping("/validate-token")
    public boolean validateToken(@RequestParam String token) {
        return userCredentialsService.verifyToken(token);
    }

    @PostMapping("/validate-user")
    public String getToken(@RequestBody UserCredentials user) {
        System.out.println("user : " + user);
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        System.out.println("authenticated?? : " + authenticate.isAuthenticated());
        if(authenticate.isAuthenticated()) {
            return userCredentialsService.generateToken(user.getUsername());
        }
        return null;
    }
    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserCredentials>> getAllUsers(){
            return ResponseEntity.ok()
                    .body(userCredentialsService.getAllUsers());
    }

    @GetMapping("/get-user-by-id/{username}")
    public ResponseEntity<UserCredentials> getUserByUsername(@PathVariable(name="username") String username){
        Optional<UserCredentials> userOptional = userCredentialsService.getUserByUsername(username);
        if(userOptional.isPresent()){
            return ResponseEntity.ok().body(userOptional.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete-user-by-username/{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable(name = "username") String username){
        return ResponseEntity.ok().body(userCredentialsService.deleteUserByUsername(username));
    }
    @PutMapping("/update-user/{username}")
    public ResponseEntity<?> updateUser(@PathVariable(name="username") String username,@RequestBody UserCredentials user){
        UserCredentials updatedUser = userCredentialsService.updateUser(username,user);
        if(updatedUser == null){
            return ResponseEntity.ok().body("user not found");
        }
        else{
            return ResponseEntity.ok().body(updatedUser);
        }
    }

    @GetMapping("/get-category-by-username/{username}")
    public ResponseEntity<FullResponse> getCategoryByUsername(@PathVariable("username") String username) throws Exception
    {
        return ResponseEntity.ok()
                .body(userCredentialsService.getCategoryByUsername(username));
    }
}
