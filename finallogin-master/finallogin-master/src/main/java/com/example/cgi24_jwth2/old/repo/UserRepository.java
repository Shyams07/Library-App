package com.example.cgi24_jwth2.old.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cgi24_jwth2.old.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
    
    User findByEmailAndSecurityQuestionAndSecurityAnswer(String email, String securityQuestion, String securityAnswer);
}