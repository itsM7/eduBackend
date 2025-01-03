package com.example.backend.controller;

import com.example.backend.entity.AssignmentEntity;
import com.example.backend.service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;

    // Endpoint to retrieve all assignments
    @GetMapping
    public List<AssignmentEntity> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    // Endpoint to create a new assignment
    @PostMapping
    public AssignmentEntity createAssignment(@Valid @RequestBody AssignmentEntity assignment) {
        return assignmentService.createAssignment(assignment);
    }

    // Endpoint to update the status of an assignment
    @PatchMapping("/{id}/status")
    public AssignmentEntity updateAssignmentStatus(@PathVariable String id, @RequestParam String status) {
        return assignmentService.updateAssignmentStatus(id, status);
    }

    // Endpoint to delete an assignment by ID
    @DeleteMapping("/{id}")
    public void deleteAssignment(@PathVariable String id) {
        assignmentService.deleteAssignment(id);
    }
}
