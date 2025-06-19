package com.vnpt.sinhvienso.service;

import com.vnpt.sinhvienso.document.Student;
import com.vnpt.sinhvienso.dto.response.StudentResponse;
import com.vnpt.sinhvienso.repository.StudentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    StudentRepository studentRepository;

    public List<Student> getUserService(){
        return studentRepository.findAll();
    }


//
//    public Page<StudentReponse> getStudentsService(String key, Pageable pageable){
//        return new PageImpl<>(page, pageable, filter.)
//    }
    public StudentResponse getUserById(ObjectId id){
        // DTO student
         Student student = new Student(studentRepository.getStudentById(id));
         StudentResponse studentDto = new StudentResponse(student.getId(), student.getRole(),
                                            student.getStudentId(), student.getSchoolCode(),
                                            student.getName(), student.getGender(), student.getEmail());

         return studentDto;
    }
}
