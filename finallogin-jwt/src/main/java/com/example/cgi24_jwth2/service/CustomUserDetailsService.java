package com.example.cgi24_jwth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cgi24_jwth2.old.model.User;
import com.example.cgi24_jwth2.old.repo.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	 @Autowired
	    private UserRepository repository;


	    
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
			User user=repository.findByEmail(email);		
	        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), (Collection<? extends GrantedAuthority>) new ArrayList());
		//return new org.springframework.security.core.userdetails.User("user", new BCryptPasswordEncoder().encode("password"), new ArrayList<>());
		}

}
