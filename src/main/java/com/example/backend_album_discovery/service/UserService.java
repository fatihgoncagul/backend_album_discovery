package com.example.backend_album_discovery.service;

import com.example.backend_album_discovery.controller.UserController;
import com.example.backend_album_discovery.exception.UserNotFoundException;
import com.example.backend_album_discovery.model.User;
import com.example.backend_album_discovery.repository.UserRepository;
import org.hibernate.engine.jdbc.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<User> getUserById(Long id) {

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        throw new UserNotFoundException("User not found");
    }


    public ResponseEntity<User> updateUserInfo(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        existingUser.setName(updatedUser.getName());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setCompany(updatedUser.getCompany());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setPhoto(updatedUser.getPhoto());

        userRepository.save(existingUser);

        return new ResponseEntity<>(existingUser,HttpStatus.OK);
    }




}
