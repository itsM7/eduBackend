package com.example.backend.repository;

import com.example.backend.entity.AssignmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends MongoRepository<AssignmentEntity, String> {
    // Additional queries can be added here if needed
}
