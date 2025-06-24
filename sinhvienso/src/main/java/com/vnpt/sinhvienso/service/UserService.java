package com.vnpt.sinhvienso.service;

import com.vnpt.sinhvienso.document.Student;
import com.vnpt.sinhvienso.dto.response.ApiResponseTest;
import com.vnpt.sinhvienso.dto.response.StudentResponse;
import com.vnpt.sinhvienso.repository.StudentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentResponse> getUserService(){
        List<Student> dtoStudentResponse = studentRepository.findAll();

        return dtoStudentResponse.stream()
                .map(student -> new StudentResponse(student.getId(), student.getRole(), student.getStudentId(), student.getSchoolCode(),
                                                    student.getName(), student.getGender(), student.getEmail()))
                .collect(toList());
    }
    public StudentResponse getUserById(ObjectId id){
        // DTO student
         Student student = new Student(studentRepository.getStudentById(id));
         StudentResponse studentDto = new StudentResponse(student.getId(), student.getRole(),
                                            student.getStudentId(), student.getSchoolCode(),
                                            student.getName(), student.getGender(), student.getEmail());

         return studentDto;
    }
}
