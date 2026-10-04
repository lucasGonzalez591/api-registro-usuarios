package com.prueba.api.controller;

import com.prueba.api.dto.UserRequest;
import com.prueba.api.dto.UserResponse;
import com.prueba.api.model.User;
import com.prueba.api.service.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> userList(){
        return ResponseEntity.ok(userService.findAll()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse>  userById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findByID(id));
    }

    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest user){
        UserResponse createdUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
