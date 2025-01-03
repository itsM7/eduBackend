package com.example.backend.service;

import com.example.backend.entity.AssignmentEntity;

import java.util.List;

public interface AssignmentService {
    // Retrieve all assignments
    List<AssignmentEntity> getAllAssignments();

    // Create a new assignment
    AssignmentEntity createAssignment(AssignmentEntity assignment);

    // Update assignment status
    AssignmentEntity updateAssignmentStatus(String id, String status);

    // Delete an assignment by ID
    void deleteAssignment(String id);
}
