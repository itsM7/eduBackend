package com.example.backend.service.impl;

import com.example.backend.entity.ScheduleEntity;
import com.example.backend.repository.ScheduleRepository;
import com.example.backend.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public List<ScheduleEntity> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public ScheduleEntity createSchedule(ScheduleEntity schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(String id) {
        scheduleRepository.deleteById(id);
    }
}
