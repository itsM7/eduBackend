package com.example.backend.entity;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "schedules")
public class ScheduleEntity {
    @Id
    private String id; // Unique identifier for the schedule

    @NotBlank(message = "Course name cannot be blank") // Course name must not be empty
    private String courseName; // Name of the course

    @NotBlank(message = "Teacher name cannot be blank") // Teacher name must not be empty
    private String teacherName; // Name of the teacher

    @NotNull(message = "Start time cannot be null") // Start time must not be null
    private LocalTime startTime; // Start time of the class

    @NotNull(message = "End time cannot be null") // End time must not be null
    private LocalTime endTime; // End time of the class

    @NotNull(message = "Date cannot be null") // Date must not be null
    @Future(message = "Date must be in the future") // Date must be in the future
    private LocalDate date; // Date of the class
}
