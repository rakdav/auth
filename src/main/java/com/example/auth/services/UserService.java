package com.example.auth.services;

import com.example.auth.entity.User;
import com.example.auth.entity.enums.ERole;
import com.example.auth.exceptions.UserExistException;
import com.example.auth.payload.request.SignupRequest;
import com.example.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService
{
    public static final Logger LOG= LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User createUser(SignupRequest userIn)
    {
        User user=new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstname());
        user.setLastname(userIn.getLastname());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRole().add(ERole.ROLE_USER);
        try
        {
            LOG.info("Saving User {}",userIn.getEmail());
            return userRepository.save(user);
        }
        catch (Exception ex)
        {
            LOG.error("Error during registration. {}", ex.getMessage());
            throw new UserExistException("The user "+user.getUsername()+" already exists. Please check credentials");
        }
    }
}
