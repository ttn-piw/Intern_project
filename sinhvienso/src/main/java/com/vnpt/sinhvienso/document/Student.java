package com.vnpt.sinhvienso.document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "sinhvien")
public class Student {
    @Id
    String id;

    String role;

    String studentId;

    String schoolCode;

    String name;

    String gender;

    String email;

    String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    Date createAt;

}
