package com.aimlpolestar.aimlPolestar.controller;

import com.aimlpolestar.aimlPolestar.model.UserDetails;
import com.aimlpolestar.aimlPolestar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
     private final  UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping ("/signup")
    public ResponseEntity<String> saveDetails (@RequestBody UserDetails userDetails) {
        return userService.signUpUser(userDetails);
    }

    @GetMapping ("/allusers")
    public List<UserDetails> allUsers () {
        return userService.findAllUserDetails();
    }

    @GetMapping ("/mobno/{mobno}")
    public ResponseEntity<UserDetails> findByMobno (@PathVariable String mobno) {
        Optional<UserDetails> userByMobno = userService.findByMobno(mobno);
        if (userByMobno.isPresent()) {
            return new ResponseEntity<UserDetails>(userByMobno.get(), HttpStatus.OK);
        }
        return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
    }

}