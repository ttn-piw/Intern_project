package com.vnpt.sinhvienso.service;

import com.vnpt.sinhvienso.document.Student;
import com.vnpt.sinhvienso.repository.StudentRepository;
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
}
