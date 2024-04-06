package com.aimlpolestar.aimlPolestar.profile.service;

import com.aimlpolestar.aimlPolestar.profile.model.UserDetails;
import com.aimlpolestar.aimlPolestar.profile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Save the user with validation for existing user (using mobno) and mobile number length
    public ResponseEntity<String> signUpUser(UserDetails userDetails) {
        // Check for existing user by id before saving
        Optional<UserDetails> existingUser = userRepository.findById(userDetails.getId());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with '" + userDetails.getId() + "' already exists.");
        }

        // Validate mobile number length
        if (userDetails.getMobno().length() != 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile number must be exactly 10 digits long.");
        }

        try {
            userRepository.save(userDetails);
            return ResponseEntity.ok("User signed up successfully.");
        } catch (DataIntegrityViolationException e) {
            // Handle potential constraint violation gracefully (e.g., log the error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred during signup. Please try again later.");
        }
    }

    // Fetching all user details
    public List<UserDetails> findAllUserDetails() {
        return userRepository.findAll();
    }

    // find by id
    public Optional<UserDetails> findById (String id) {
        return userRepository.findById(id);
    }
}

