package com.vnpt.sinhvienso.repository;

import com.vnpt.sinhvienso.document.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User,String>{
//    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
