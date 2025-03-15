package com.client_management_system.controller;

import com.client_management_system.Entity.Meeting;
import com.client_management_system.service.MeetingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;


    @PostMapping("/meeting")
    public ResponseEntity<?> scheduleMeeting(
            @RequestParam Long clientId,
            @RequestBody Meeting meeting,
            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(meetingService.scheduleMeeting(clientId, meeting));
    }


    @GetMapping("/meetings")
    public ResponseEntity<List<Meeting>> getAllMeetings() {
        return ResponseEntity.ok(meetingService.getAllMeetings());
    }


    @GetMapping("/meeting/{id}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable Long id) {
        return ResponseEntity.ok(meetingService.getMeetingById(id));
    }

    @DeleteMapping("/meeting/{id}")
    public ResponseEntity<?> cancelMeeting(@PathVariable Long id) {
        try {
            meetingService.cancelMeeting(id);
            return new ResponseEntity<>("/meeting id " + id + " deleted successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/meeting/{id}")
    public ResponseEntity<Meeting> updateMeeting(@PathVariable Long id, @RequestBody Meeting meeting) {
        return ResponseEntity.ok(meetingService.updateMeeting(id, meeting));
    }



}
