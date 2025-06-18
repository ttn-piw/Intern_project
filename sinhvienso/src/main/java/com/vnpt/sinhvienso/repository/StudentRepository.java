package com.vnpt.sinhvienso.repository;

import com.vnpt.sinhvienso.document.Student;
import com.vnpt.sinhvienso.dto.response.StudentResponse;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findByName(String name);

    @Query(value = "{'id': ?0}")
    Student getStudentById(ObjectId id);

    Student getStudentsByEmail(String email);
}
