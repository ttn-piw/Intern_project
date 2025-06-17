package com.vnpt.sinhvienso.controller.v1;

import com.vnpt.sinhvienso.document.Student;
import com.vnpt.sinhvienso.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("")
    public List<Student> getStudents(){
        return studentService.getStudentsService();
    }
}
