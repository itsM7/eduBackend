package com.example.backend.repository;

import com.example.backend.entity.AnnouncementEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnnouncementRepository extends MongoRepository<AnnouncementEntity, String> {
    // Custom query to find announcements active between a specific time range
    List<AnnouncementEntity> findByEndDateAfterAndStartDateBefore(LocalDate now, LocalDate nowAgain);
}
