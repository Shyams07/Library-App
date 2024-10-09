package com.example.cgi24_jwth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cgi24_jwth2.old.model.AuthRequest;
import com.example.cgi24_jwth2.old.model.User;
import com.example.cgi24_jwth2.old.repo.UserRepository;
import com.example.cgi24_jwth2.service.UserService;
import com.example.cgi24_jwth2.util.old.JwtUtil;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    
    @Autowired 
    UserService userService;
    
    

    @GetMapping("/test")
    public String welcomeTest() {
        return "Welcome to JWT TOKENS !!";
    }

    
    @GetMapping("/cgitest")
    public String testingEndpoint() {
        return "Welcome to JWT TOKENS !!";
    }
    

    @GetMapping("/home")
    public String testingHomeEndpoint() {
        return "Welcome to JWT TOKENS Home page secured!!";
    }

    
    
    @GetMapping("na/cgitest")
    public String testingNewEndpoint() {
        return "Welcome to JWT TOKENS insecured!!";
    }
    
    
    
    @Autowired
    UserRepository userRepository;
    
	
    @PostMapping("na/signup")
    public User signup(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("na/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody User user) {
        User foundUser = userService.findByEmailAndSecurityQuestionAndSecurityAnswer(
            user.getEmail(), user.getSecurityQuestion(), user.getSecurityAnswer());
        
        if (foundUser != null) {
            return ResponseEntity.ok().body("User verified. You can now reset your password.");
        } else {
            return ResponseEntity.badRequest().body("Invalid email or security details.");
        }
    }

    @PutMapping("na/reset")
    public ResponseEntity<?> resetPassword(@RequestBody User user) {
        User updatedUser = userService.resetPassword(user);
        if (updatedUser != null) {
            return ResponseEntity.ok().body("Password reset successfully.");
        } else {
            return ResponseEntity.badRequest().body("Unable to reset password. User not found.");
        }
    }
    
    
    @PostMapping("na/login")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }
   
}