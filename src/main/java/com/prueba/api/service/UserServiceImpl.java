package com.prueba.api.service;

import com.prueba.api.dto.PhoneDto;
import com.prueba.api.dto.UserRequest;
import com.prueba.api.dto.UserResponse;
import com.prueba.api.model.Phone;
import com.prueba.api.model.User;
import com.prueba.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findByID(Long id) {
        User foundUser = userRepository.findById(id).orElse(null);
        if (foundUser != null){
            return toResponse(foundUser);
        }
        return null;
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setActive(true);
        user.setCreated(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setToken(UUID.randomUUID().toString());

        if (userRequest.phones() != null) {
            List<Phone> phones = userRequest.phones().stream().map(p -> {
                Phone phone = new Phone();
                phone.setNumber(p.number());
                phone.setCitycode(p.citycode());
                phone.setContrycode(p.contrycode());
                return phone;
            }).collect(Collectors.toList());

            user.setPhones(phones);
        }
        System.out.println(user.toString());
        User savedUser = userRepository.save(user);

        return toResponse(savedUser);

    }

    private UserResponse toResponse(User user) {
        List<PhoneDto> phoneResponses = user.getPhones() != null
                ? user.getPhones().stream()
                .map(p -> new PhoneDto( p.getNumber(), p.getCitycode(), p.getContrycode()))
                .collect(Collectors.toList())
                : List.of();

        return new UserResponse(
                user.getId(),
                user.getCreated(),
                user.getModified(),
                user.getLastLogin(),
                user.getToken(),
                user.isActive(),
                user.getName(),
                user.getEmail(),
                phoneResponses
        );
    }
}
