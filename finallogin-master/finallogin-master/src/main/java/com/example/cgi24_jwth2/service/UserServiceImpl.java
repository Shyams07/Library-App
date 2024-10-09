package com.example.cgi24_jwth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cgi24_jwth2.old.model.User;
import com.example.cgi24_jwth2.old.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByEmailAndSecurityQuestionAndSecurityAnswer(String email, String securityQuestion, String securityAnswer) {
        return userRepository.findByEmailAndSecurityQuestionAndSecurityAnswer(email, securityQuestion, securityAnswer);
    }

    @Override
    public User resetPassword(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(existingUser);
        }
        return null;
    }
}