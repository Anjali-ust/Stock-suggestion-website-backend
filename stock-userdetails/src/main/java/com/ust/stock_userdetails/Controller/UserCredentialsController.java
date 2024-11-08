package com.ust.stock_userdetails.Controller;


import com.ust.stock_userdetails.Model.UserCredentials;
import com.ust.stock_userdetails.Service.JwtService;
import com.ust.stock_userdetails.Service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
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

    @GetMapping("/validate/token")
    public boolean validateToken(@RequestParam String token) {
        return userCredentialsService.verifyToken(token);
    }

    @PostMapping("/validate/user")
    public String getToken(@RequestBody UserCredentials user) {
        System.out.println("user : " + user);
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        System.out.println("authenticated?? : " + authenticate.isAuthenticated());
        if(authenticate.isAuthenticated()) {
            return userCredentialsService.generateToken(user.getUsername());
        }
        return null;
    }
}
