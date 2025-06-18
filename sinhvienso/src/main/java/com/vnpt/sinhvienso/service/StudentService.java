package com.vnpt.sinhvienso.service;

import com.vnpt.sinhvienso.document.Student;
import com.vnpt.sinhvienso.dto.request.LoginRequest;
import com.vnpt.sinhvienso.dto.response.AuthResponse;
import com.vnpt.sinhvienso.dto.response.StudentResponse;
import com.vnpt.sinhvienso.repository.StudentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<Student> getStudentsService(){
        return studentRepository.findAll();
    }

    public AuthResponse login(LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        Student student = studentRepository.getStudentsByEmail(email);

        if (student == null) {
            return new AuthResponse(401, "Email does not exist", false);
        }

        if (!password.equals(student.getPassword())) {
            return new AuthResponse(401, "Invalid password", false);
        }

        return new AuthResponse(200, "Login successful", true);
    }

//
//    public Page<StudentReponse> getStudentsService(String key, Pageable pageable){
//        return new PageImpl<>(page, pageable, filter.)
//    }
    public StudentResponse getStudentById(ObjectId id){
        // DTO student
         Student student = new Student(studentRepository.getStudentById(id));
         StudentResponse studentDto = new StudentResponse(student.getId(), student.getRole(),
                                            student.getStudentId(), student.getSchoolCode(),
                                            student.getName(), student.getGender(), student.getEmail());

         return studentDto;
    }
}
