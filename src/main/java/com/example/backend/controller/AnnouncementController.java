package com.example.backend.controller;

import com.example.backend.entity.AnnouncementEntity;
import com.example.backend.service.AnnouncementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;

    // Endpoint to fetch all active announcements
    @GetMapping
    public List<AnnouncementEntity> getActiveAnnouncements() {
        return announcementService.getActiveAnnouncements();
    }

    // Endpoint to create a new announcement
    @PostMapping
    public AnnouncementEntity createAnnouncement(@Valid @RequestBody AnnouncementEntity announcement) {
        return announcementService.createAnnouncement(announcement);
    }

    // Endpoint to delete an announcement by ID
    @DeleteMapping("/{id}")
    public void deleteAnnouncement(@PathVariable String id) {
        announcementService.deleteAnnouncement(id);
    }
}
