package com.prueba.api.service;

import com.prueba.api.dto.UserRequest;
import com.prueba.api.dto.UserResponse;
import com.prueba.api.model.User;

import java.util.List;

public interface UserService {

    List<UserResponse>findAll();
    UserResponse findByID(Long id);
    UserResponse save(UserRequest user);

}
