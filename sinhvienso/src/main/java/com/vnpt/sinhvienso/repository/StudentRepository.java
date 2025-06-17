package com.vnpt.sinhvienso.repository;

import com.vnpt.sinhvienso.document.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findByName(String name);
}
