package com.blog.services;

import com.blog.payload.LoginDto;
import com.blog.payload.SignUpDto;

public interface UserService {

    void saveUser(SignUpDto userDto);
}
