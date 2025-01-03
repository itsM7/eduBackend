package com.example.backend.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "resources")
public class ResourceEntity {
    @Id
    private String id; // Unique identifier for the resource

    @NotBlank(message = "Title cannot be blank") // Title must not be empty
    private String title; // Resource title

    @NotBlank(message = "Description cannot be blank") // Description must not be empty
    private String description; // Resource description

    @NotBlank(message = "Type cannot be blank") // Type must not be empty
    private String type; // Resource type (e.g., PDF, Video, Link)

    @NotBlank(message = "URL cannot be blank") // URL must not be empty
    private String url; // Link to the resource

    @NotBlank(message = "Course name cannot be blank") // Course name must not be empty
    private String courseName; // Name of the course the resource belongs to

    private LocalDate uploadDate; // Date when the resource was uploaded
}
