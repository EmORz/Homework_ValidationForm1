package com.example.Homework_ValidationForm1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User userByUsername = userRepository.getUserByUsername(usernameOrEmail);
        if (userByUsername != null) {
            return new MyUserDetails(userByUsername);
        }
        User userbyEmail = userRepository.getUserByEmail(usernameOrEmail);
        if (userbyEmail != null) {
            return new MyUserDetails(userbyEmail);
        }else {
            throw new UsernameNotFoundException("Could not find user");
        }
    }
}
