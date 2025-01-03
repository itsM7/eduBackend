package com.example.backend.controller;

import com.example.backend.entity.ScheduleEntity;
import com.example.backend.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    // Endpoint to retrieve all schedules
    @GetMapping
    public List<ScheduleEntity> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    // Endpoint to create a new schedule
    @PostMapping
    public ScheduleEntity createSchedule(@Valid @RequestBody ScheduleEntity schedule) {
        return scheduleService.createSchedule(schedule);
    }

    // Endpoint to delete a schedule by ID
    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable String id) {
        scheduleService.deleteSchedule(id);
    }
}
