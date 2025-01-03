package com.example.backend.service;

import com.example.backend.entity.ScheduleEntity;

import java.util.List;

public interface ScheduleService {
    // Retrieve all schedules
    List<ScheduleEntity> getAllSchedules();

    // Create a new schedule
    ScheduleEntity createSchedule(ScheduleEntity schedule);

    // Delete a schedule by ID
    void deleteSchedule(String id);
}
