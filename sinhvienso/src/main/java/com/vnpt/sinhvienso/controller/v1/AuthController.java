package com.vnpt.sinhvienso.controller.v1;

import com.nimbusds.jose.JOSEException;
import com.vnpt.sinhvienso.dto.request.IntrospectRequest;
import com.vnpt.sinhvienso.dto.request.LoginRequest;
import com.vnpt.sinhvienso.dto.request.RegisterRequest;
import com.vnpt.sinhvienso.dto.response.ApiResponse;
import com.vnpt.sinhvienso.dto.response.ApiResponseTest;
import com.vnpt.sinhvienso.dto.response.AuthResponse;
import com.vnpt.sinhvienso.dto.response.IntrospectResponse;
import com.vnpt.sinhvienso.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authenticationService.login(request);
        HttpStatus status = response.getAuthenticated() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest request) {
        ApiResponse response = authenticationService.register(request);
        HttpStatus status = response.getStatus() == 200 ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/introspect")
    public ApiResponseTest<IntrospectResponse> verifyToken(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {

        //"valid" : true
        var result = authenticationService.introspect(request);
        return ApiResponseTest.<IntrospectResponse>builder()
                .result(result)   // Result
                .build();
    }
}
