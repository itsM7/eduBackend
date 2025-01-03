package com.example.backend.repository;

import com.example.backend.entity.ResourceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends MongoRepository<ResourceEntity, String> {
    // Custom query to find resources by course name
    List<ResourceEntity> findByCourseName(String courseName);
}
