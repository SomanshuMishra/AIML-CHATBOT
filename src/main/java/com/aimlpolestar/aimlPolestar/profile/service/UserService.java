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


    // update the user details
    public ResponseEntity<String> updateUser(String id, UserDetails userDetails) {
        // check if the user exists or not
        Optional<UserDetails> existingUser = userRepository.findById(id);

        // if present
        if (existingUser.isPresent()) {

            UserDetails updatedUser = existingUser.get();
            updatedUser.setMobno(userDetails.getMobno());
            updatedUser.setFname(userDetails.getFname());
            updatedUser.setLname(userDetails.getLname());
            updatedUser.setAge(userDetails.getAge());
            updatedUser.setSex(userDetails.getSex());
            updatedUser.setPasswd(userDetails.getPasswd());


            // then update the updated details
            userRepository.save(updatedUser);
            return new ResponseEntity<>("User details updated successfully", HttpStatus.OK);
        } else {
            // if not present then return with an error
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
    public String changeActiveStatusById(String id, String newActiveStatus) {
        UserDetails userDetails = userRepository.findById(id).orElse(null);
        if (userDetails != null) {
            userDetails.setActive(newActiveStatus);
            userRepository.save(userDetails);
            return "Active status updated successfully for user with id: " + id;
        } else {
            return "User with id: " + id + " not found";
        }
    }



}

