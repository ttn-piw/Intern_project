package com.vnpt.sinhvienso.repository;

import com.vnpt.sinhvienso.document.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface PostRepository extends MongoRepository<Post, ObjectId> {

}
