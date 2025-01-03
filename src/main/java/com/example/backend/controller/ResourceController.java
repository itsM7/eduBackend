package com.example.backend.controller;

import com.example.backend.entity.ResourceEntity;
import com.example.backend.service.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    // Endpoint to retrieve all resources
    @GetMapping
    public List<ResourceEntity> getAllResources() {
        return resourceService.getAllResources();
    }

    // Endpoint to retrieve resources by course name
    @GetMapping("/course")
    public List<ResourceEntity> getResourcesByCourseName(@RequestParam String courseName) {
        return resourceService.getResourcesByCourseName(courseName);
    }

    // Endpoint to create a new resource
    @PostMapping
    public ResourceEntity createResource(@Valid @RequestBody ResourceEntity resource) {
        return resourceService.createResource(resource);
    }

    // Endpoint to delete a resource by ID
    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable String id) {
        resourceService.deleteResource(id);
    }
}
