package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Future;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "announcements") // MongoDB collection name
public class AnnouncementEntity {
    @Id
    private String id; // Unique identifier for the announcement

    @NotBlank(message = "Title cannot be blank") // Title must not be empty
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title; // Announcement title

    @NotBlank(message = "Description cannot be blank") // Description must not be empty
    private String description; // Detailed description of the announcement

    @NotBlank(message = "Category cannot be blank") // Category must not be empty
    private String category; // Category of the announcement (e.g., Academic, Administrative)

    private LocalDate startDate; // Start date of the announcement

    @Future(message = "End date must be in the future") // Ensures the date is in the future
    private LocalDate endDate; // End date of the announcement
}
