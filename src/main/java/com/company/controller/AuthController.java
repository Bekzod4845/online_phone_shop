package com.company.controller;

import com.company.common.ApiResponse;
import com.company.dto.*;
import com.company.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<ApiResponse> registration(@RequestBody RegistrationDTO dto) {
         authService.registration(dto);
        return new ResponseEntity<>(new ApiResponse(true,"successfully registration"), HttpStatus.CREATED);
    }


}
