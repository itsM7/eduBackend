package com.example.backend.repository;

import com.example.backend.entity.ScheduleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends MongoRepository<ScheduleEntity, String> {
    // Additional queries can be added here if needed
}
