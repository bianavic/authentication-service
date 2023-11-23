package org.edu.fabs.authenticationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.edu.fabs.authenticationservice.auth.AuthenticationService;
import org.edu.fabs.authenticationservice.auth.JwtService;
import org.edu.fabs.authenticationservice.domain.User;
import org.edu.fabs.authenticationservice.domain.dto.LoginResponse;
import org.edu.fabs.authenticationservice.domain.dto.LoginUserRequest;
import org.edu.fabs.authenticationservice.domain.dto.RegisterUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@Tag(name = "Authentication", description = "The user registration and authentication API.")
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    @Operation(summary = "register a new user")
    public ResponseEntity<User> register(@RequestBody RegisterUserRequest userRequest) {
        User registeredUser = authenticationService.signup(userRequest);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    @Operation(summary = "authenticate an user")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserRequest userRequest) {
        User authenticatedUser = authenticationService.authenticate(userRequest);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        long expiresIn = jwtService.getExpirationTime();

        LoginResponse response = new LoginResponse(jwtToken, expiresIn);
        return ResponseEntity.ok(response);
    }

}
