package com.vnpt.sinhvienso.controller.v1;

import com.vnpt.sinhvienso.dto.request.UserRegisterRequest;
import com.vnpt.sinhvienso.dto.response.UserResponse;
import com.vnpt.sinhvienso.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        UserResponse response = authService.registerUser(request);
        return ResponseEntity.ok(response);
    }
}