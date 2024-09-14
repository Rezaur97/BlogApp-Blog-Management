package com.blog.services.impl;

import com.blog.entities.Role;
import com.blog.entities.User;
import com.blog.exceptions.BlogAPIException;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payload.LoginDto;
import com.blog.payload.SignUpDto;
import com.blog.repositries.RoleRepository;
import com.blog.repositries.UserRepository;
import com.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private ModelMapper mapper;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }





    @Override
    public void saveUser(SignUpDto userDto) {
        // add check for username exists in a DB
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }

        // add check for email exists in DB
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }

        // map dto to entity
        User user = dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role roles = roleRepository.findByRoleName("ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
    }

    private User dtoToUser(SignUpDto signUpDto) {
        return mapper.map(signUpDto,User.class);
    }
}
