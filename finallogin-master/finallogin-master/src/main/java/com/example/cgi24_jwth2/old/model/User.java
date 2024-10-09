package com.example.cgi24_jwth2.old.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {

    @Id
    private String email;
    private String password;
    private String name;
    private String securityQuestion;
    private String securityAnswer;

    // Constructors

    public User() {}

    public User(String email, String password, String name, String securityQuestion, String securityAnswer) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", password=" + password + ", name=" + name + 
               ", securityQuestion=" + securityQuestion + ", securityAnswer=" + securityAnswer + "]";
    }
}