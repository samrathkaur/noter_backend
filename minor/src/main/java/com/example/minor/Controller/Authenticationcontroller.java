package com.example.minor.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.minor.Model.Login;
import com.example.minor.Model.User;
import com.example.minor.Repository.UserRepository;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("auth")
public class Authenticationcontroller {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        for (User u : userRepository.findAll()) {
            if (u.getEmail().equals(user.getEmail())) {
                return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
            }
        }
        for(User u : userRepository.findAll()) {
            if(u.getName().equals(user.getName())) {
                return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
            }
        }
        userRepository.save(user);
        return new ResponseEntity<>(("User registered successfully!"), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Login login) {
        for (User u : userRepository.findAll()) {
            if (u.getEmail().equals(login.getEmail()) && u.getPassword().equals(login.getPassword())) {
                return new ResponseEntity<>(("User logged in successfully!"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(("Invalid credentials/User not found"), HttpStatus.NOT_FOUND);
    }

}