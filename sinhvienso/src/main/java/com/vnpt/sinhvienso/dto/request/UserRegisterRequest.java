package com.vnpt.sinhvienso.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterRequest {

    @NotBlank(message = "Tên không được để trống")
    @Size(max = 100, message = "Tên không được dài quá 100 ký tự")
    String name;

    @NotBlank(message = "Role không được để trống")
    String role;

    @Value("${some.key:}")
    String studentId;

    @Value("${some.key:}")
    String schoolCode;

    @Value("${some.key:Male}")
    String gender;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    String password;

    @NotBlank(message = "Xác nhận mật khẩu không được để trống")
    String confirmPassword;
}
