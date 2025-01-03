package com.example.backend.entity;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "assignments")
public class AssignmentEntity {
    @Id
    private String id; // Unique identifier for the assignment

    @NotBlank(message = "Title cannot be blank") // Title must not be empty
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title; // Assignment title

    @NotBlank(message = "Description cannot be blank") // Description must not be empty
    private String description; // Detailed description of the assignment

    @Future(message = "Deadline must be in the future") // Deadline must be in the future
    private LocalDate deadline; // Assignment deadline

    @NotBlank(message = "Status cannot be blank") // Status must not be empty
    private String status; // Assignment status (e.g., "Submitted", "Not Submitted")
}
