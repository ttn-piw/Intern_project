package com.vnpt.sinhvienso.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.vnpt.sinhvienso.document.Student;
import com.vnpt.sinhvienso.dto.request.LoginRequest;
import com.vnpt.sinhvienso.dto.response.AuthResponse;
import com.vnpt.sinhvienso.repository.StudentRepository;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class AuthenticationService {
    @Autowired
    StudentRepository studentRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Value("${jwt.expiration}")
    private long expiration;

    public AuthResponse login(LoginRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        String email = request.getEmail();
        String password = request.getPassword();

        Student student = studentRepository.getStudentsByEmail(email);
        var token = generateToken(email);

        if (student == null) {
            return new AuthResponse(401, "Email does not exist", false,"","",0);
        }

        if (!passwordEncoder.matches(password,student.getPassword())) {
            return new AuthResponse(401, "Invalid password", false, "","",0);
        }

        return new AuthResponse(200, "Login successful", true, token,"Bearer", expiration);

    }

    private String generateToken(String username){
        //Build header with HS512 Algorithm
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        //Define Claim
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("sinhvienso.com")
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + expiration))
                .claim("customerClaim", "Customer")
                .build();

        //Payload
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        //JWT = header + payload
        JWSObject jwsObject = new JWSObject(jwsHeader,payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes(StandardCharsets.UTF_8)));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }
}
