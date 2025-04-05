package com.example.eventmanagement.service;

import com.example.eventmanagement.model.Venue;
import com.example.eventmanagement.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {
    @Autowired
    private VenueRepository venueRepository;

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).orElse(null);
    }

    public Venue addVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public Venue updateVenue(Long id, Venue updatedVenue) {
        return venueRepository.findById(id)
                .map(venue -> {
                    venue.setName(updatedVenue.getName());
                    venue.setLocation(updatedVenue.getLocation());
                    venue.setCapacity(updatedVenue.getCapacity());
                    return venueRepository.save(venue);
                })
                .orElse(null);
    }

    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }
}
