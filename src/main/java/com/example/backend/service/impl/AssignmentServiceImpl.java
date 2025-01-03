package com.example.backend.service.impl;

import com.example.backend.entity.AssignmentEntity;
import com.example.backend.repository.AssignmentRepository;
import com.example.backend.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;

    @Override
    public List<AssignmentEntity> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    @Override
    public AssignmentEntity createAssignment(AssignmentEntity assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public AssignmentEntity updateAssignmentStatus(String id, String status) {
        AssignmentEntity assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found with id: " + id));
        assignment.setStatus(status);
        return assignmentRepository.save(assignment);
    }

    @Override
    public void deleteAssignment(String id) {
        assignmentRepository.deleteById(id);
    }
}
