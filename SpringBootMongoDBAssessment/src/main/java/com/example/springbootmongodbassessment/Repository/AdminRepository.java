package com.example.springbootmongodbassessment.Repository;

import com.example.springbootmongodbassessment.Model.Admin;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminRepository extends MongoRepository<Admin, ObjectId> {
    @Query("{email: \"?0\"}")
    List<Admin> getUserByEmail(String email);
}



