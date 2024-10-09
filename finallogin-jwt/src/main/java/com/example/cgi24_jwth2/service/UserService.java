package com.example.cgi24_jwth2.service;

import org.springframework.stereotype.Service;

import com.example.cgi24_jwth2.old.model.User;

@Service
public interface UserService {

    User saveUser(User user);
    
    User findByEmailAndSecurityQuestionAndSecurityAnswer(String email, String securityQuestion, String securityAnswer);
    
    User resetPassword(User user);
}