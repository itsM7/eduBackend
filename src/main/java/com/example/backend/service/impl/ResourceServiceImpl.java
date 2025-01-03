package com.example.backend.service.impl;

import com.example.backend.entity.ResourceEntity;
import com.example.backend.repository.ResourceRepository;
import com.example.backend.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;

    @Override
    public List<ResourceEntity> getAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public List<ResourceEntity> getResourcesByCourseName(String courseName) {
        return resourceRepository.findByCourseName(courseName);
    }

    @Override
    public ResourceEntity createResource(ResourceEntity resource) {
        // Automatically set upload date to today's date if not provided
        resource.setUploadDate(LocalDate.now());
        return resourceRepository.save(resource);
    }

    @Override
    public void deleteResource(String id) {
        resourceRepository.deleteById(id);
    }
}
