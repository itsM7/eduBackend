package com.example.backend.service.impl;

import com.example.backend.entity.AnnouncementEntity;
import com.example.backend.repository.AnnouncementRepository;
import com.example.backend.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    @Override
    public List<AnnouncementEntity> getActiveAnnouncements() {
        LocalDate now = LocalDate.now();
        return announcementRepository.findByEndDateAfterAndStartDateBefore(now, now);
    }

    @Override
    public AnnouncementEntity createAnnouncement(AnnouncementEntity announcement) {
        return announcementRepository.save(announcement);
    }

    @Override
    public void deleteAnnouncement(String id) {
        announcementRepository.deleteById(id);
    }
}
