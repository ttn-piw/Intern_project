package com.vnpt.sinhvienso.controller.v1;

import com.vnpt.sinhvienso.dto.request.LoginRequest;
import com.vnpt.sinhvienso.dto.response.ApiResponse;
import com.vnpt.sinhvienso.dto.response.AuthResponse;
import com.vnpt.sinhvienso.dto.response.UserResponse;
import com.vnpt.sinhvienso.service.StudentService;
import com.vnpt.sinhvienso.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    Logger logger  = LoggerFactory.getLogger(UserController.class);

    @GetMapping("")
    public ResponseEntity<Object> getUsers(HttpServletRequest request,
                                           @RequestParam(value = "key", required= true)
                                           String key,
                                           Pageable pageable) {
        Boolean isAuth = true;

        String requestPath = request.getMethod() + " " + request.getRequestURI() + (request.getQueryString() != null
                                                                                    ? "?" + request.getQueryString()
                                                                                    : "");
        Page<UserResponse> page = userService.getUsers(key, pageable);
        logger.info(requestPath);
        try {
            return (isAuth) ? ResponseEntity.ok(page)
                            : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token has expired");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL to get users: " + ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = studentService.login(request);
        HttpStatus status = response.getAuthenticated() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(response);
    }

}
