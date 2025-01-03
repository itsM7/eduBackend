package com.example.backend.service;

import com.example.backend.entity.AnnouncementEntity;

import java.util.List;

public interface AnnouncementService {
    // Retrieve all active announcements
    List<AnnouncementEntity> getActiveAnnouncements();

    // Create a new announcement
    AnnouncementEntity createAnnouncement(AnnouncementEntity announcement);

    // Delete an announcement by its ID
    void deleteAnnouncement(String id);
}
