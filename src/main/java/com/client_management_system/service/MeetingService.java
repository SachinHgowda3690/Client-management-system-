package com.client_management_system.service;

import com.client_management_system.Entity.Client;
import com.client_management_system.Entity.Meeting;
import com.client_management_system.repository.ClientRepository;
import com.client_management_system.repository.MeetingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Meeting scheduleMeeting(Long clientId, Meeting meeting) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        meeting.setClient(client);
        return meetingRepository.save(meeting);
    }


    //to get all meetings
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    public Meeting getMeetingById(Long id) {
        return meetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));
    }

    public void cancelMeeting(Long id) {
        if(!meetingRepository.existsById(id)){
            throw new EntityNotFoundException("meeting with id " + id + " not found");
        }

        meetingRepository.deleteById(id);
    }

    public Meeting updateMeeting(Long id, Meeting meeting)
    {
        Meeting existingMeeting = meetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));
        if(existingMeeting!=null){

            existingMeeting.setMeetingTime(meeting.getMeetingTime());
            existingMeeting.setAgenda(meeting.getAgenda());
            existingMeeting.setNotes(meeting.getNotes());
            return meetingRepository.save(existingMeeting);
        }
        return null;
    }
}
