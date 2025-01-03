package com.example.backend.service;

import com.example.backend.entity.ResourceEntity;

import java.util.List;

public interface ResourceService {
    // Retrieve all resources
    List<ResourceEntity> getAllResources();

    // Retrieve resources by course name
    List<ResourceEntity> getResourcesByCourseName(String courseName);

    // Create a new resource
    ResourceEntity createResource(ResourceEntity resource);

    // Delete a resource by ID
    void deleteResource(String id);
}
