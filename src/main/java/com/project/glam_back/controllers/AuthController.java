package com.project.glam_back.controllers;

import com.project.glam_back.daos.UserDao;
import com.project.glam_back.entities.User;
import com.project.glam_back.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserDao userDao, PasswordEncoder encoder, JwtUtil jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User userWithId = userDao.findByEmail(user.getEmail());

        return jwtUtils.generateToken(userWithId.getId(), userDetails.getUsername(), userWithId.getRole());
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        boolean alreadyExists = userDao.existsByEmail(user.getEmail());
        if (alreadyExists) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        User newUser = new User(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                encoder.encode(user.getPassword()),
                "USER"
        );
        User isUserSaved = userDao.save(newUser);
        return isUserSaved != null
                ? ResponseEntity.ok("User registered successfully!")
                : ResponseEntity.badRequest().body("Error: User registration failed!");
    }
}