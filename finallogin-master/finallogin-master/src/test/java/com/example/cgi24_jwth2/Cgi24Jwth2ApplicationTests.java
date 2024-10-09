package com.example.cgi24_jwth2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.example.cgi24_jwth2.old.model.User;
import com.example.cgi24_jwth2.service.UserService;
import com.example.cgi24_jwth2.controller.UserController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class Cgi24Jwth2ApplicationTests {

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        // Set up any necessary preconditions before each test, if needed.
    }

    @Test
    void contextLoads() {
        // Test to ensure the application context loads successfully.
    }

    @Test
    void testSignup() {
        // Test to verify that a user can successfully sign up.
        User user = new User("test@example.com", "password", "Test User", "What's your favorite color?", "Blue");
        when(userService.saveUser(user)).thenReturn(user);

        User result = userController.signup(user);
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    void testWelcomeTest() {
        // Test to verify the welcome message returned by the welcomeTest method.
        String result = userController.welcomeTest();
        assertEquals("Welcome to JWT TOKENS !!", result);
    }

    @Test
    void testForgotPassword() {
        // Test to ensure the forgotPassword method verifies the user and allows password reset.
        User user = new User("test@example.com", null, null, "What's your favorite color?", "Blue");
        when(userService.findByEmailAndSecurityQuestionAndSecurityAnswer(
            user.getEmail(), user.getSecurityQuestion(), user.getSecurityAnswer()))
            .thenReturn(user);

        ResponseEntity<?> response = userController.forgotPassword(user);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User verified. You can now reset your password.", response.getBody());
    }

    @Test
    void testSignupWithNullEmail() {
        // Test to verify that signing up with a null email fails.
        User user = new User(null, "password", "Test User", "What's your favorite color?", "Blue");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userController.signup(user);
        });
        Assertions.assertEquals("This message will cause the test to fail", "Actual message");
    }
}
