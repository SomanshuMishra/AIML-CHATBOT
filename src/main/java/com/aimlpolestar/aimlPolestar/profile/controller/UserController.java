package com.aimlpolestar.aimlPolestar.profile.controller;

import com.aimlpolestar.aimlPolestar.profile.model.UserDetails;
import com.aimlpolestar.aimlPolestar.profile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> saveDetails(@RequestBody UserDetails userDetails) {
        return userService.signUpUser(userDetails);
    }

    @GetMapping("/allusers")
    public List<UserDetails> allUsers() {
        return userService.findAllUserDetails();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDetails> findById(@PathVariable int id) {
        Optional<UserDetails> userById = userService.findById(id);
        if (userById.isPresent()) {
            return new ResponseEntity<UserDetails>(userById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
    }

//     update the user
     @PutMapping("/update/{primaryUser}")    // primary_user
     public ResponseEntity<String> updateDetails(@PathVariable String primaryUser,
     @RequestBody UserDetails userDetails) {
         ResponseEntity<String> response = userService.updateUser(primaryUser, userDetails);
         return response;
     }

     @PutMapping("/change_active_status")   // through primary_user
     public ResponseEntity<String> changeActiveStatusById(@RequestBody UserDetails
     userDetails) {
     String message = userService.changeActiveStatusById(userDetails.getPrimaryuser(),
     userDetails.getActive());
     return ResponseEntity.ok(message);
     }

}