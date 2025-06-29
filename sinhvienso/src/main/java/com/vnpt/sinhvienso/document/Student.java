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
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "sinhvien")
public class Student {
    @Id
    String id;

    Set<String> roles;

    String studentId;

    String schoolCode;

    String name;

    String gender;

    String email;

    String password;
  
    String createAt;

    public Student(Student otherStudent){
        this.id = otherStudent.getId();
        this.roles = otherStudent.getRoles();
        this.studentId = otherStudent.getStudentId();
        this.schoolCode = otherStudent.getSchoolCode();
        this.name = otherStudent.getName();
        this.email = otherStudent.getEmail();
        this.gender = otherStudent.getGender();
        this.password = otherStudent.getPassword();
    }
}
