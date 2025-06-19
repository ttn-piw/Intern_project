package com.vnpt.sinhvienso.service;

import com.vnpt.sinhvienso.document.Student;
import com.vnpt.sinhvienso.dto.request.UserRegisterRequest;
import com.vnpt.sinhvienso.dto.response.UserResponse;
import com.vnpt.sinhvienso.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AuthService {

    @Autowired
    StudentRepository studentRepository;

    public UserResponse registerUser(UserRegisterRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Mật khẩu và xác nhận không khớp");
        }

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        Student user = new Student();
        user.setRole(request.getRole());
        user.setStudentId(request.getStudentId());
        user.setSchoolCode(request.getSchoolCode());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setGender(request.getGender());
        user.setCreateAt(formatter.format(new Date()));

        studentRepository.save(user);

        return new UserResponse(user.getId().toString(), user.getName(), user.getEmail());
    }
}
