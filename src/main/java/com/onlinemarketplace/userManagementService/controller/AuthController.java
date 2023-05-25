package com.onlinemarketplace.userManagementService.controller;

import com.onlinemarketplace.userManagementService.requestData.LoginRequestData;
import com.onlinemarketplace.userManagementService.requestData.RegisterRequestData;
import com.onlinemarketplace.userManagementService.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

    private AuthService authService ;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //build rest api for log-in
    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<String> login(@RequestBody LoginRequestData loginRequestData) {
        return new ResponseEntity<>(authService.login(loginRequestData), HttpStatus.OK);
    }

    // build rest api for registration
    @PostMapping( value={"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterRequestData registerRequestData) {
        return new ResponseEntity<>(authService.register(registerRequestData),HttpStatus.CREATED) ;
    }
}
