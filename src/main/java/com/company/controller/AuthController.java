package com.company.controller;

import com.company.dto.*;
import com.company.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO dto) {
        ProfileDTO profileDTO = authService.login(dto);
        return ResponseEntity.ok(profileDTO);
    }

    @PostMapping("/registration")
    public ResponseEntity<ProfileDTO> registration(@RequestBody RegistrationDTO dto) {
        ProfileDTO registration = authService.registration(dto);
        return ResponseEntity.ok(registration);
    }

    @PostMapping("/verification")
    public ResponseEntity<String> login(@RequestBody VerificationDTO dto) {
        String response = authService.verification(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/resend/{phone}")
    public ResponseEntity<ResponseInfoDTO> resendSms(@PathVariable("phone") String phone) {
        ResponseInfoDTO response = authService.resendSms(phone);
        return ResponseEntity.ok(response);
    }






}
