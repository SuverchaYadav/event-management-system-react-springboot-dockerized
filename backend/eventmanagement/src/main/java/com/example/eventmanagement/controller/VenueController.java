package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.Venue;
import com.example.eventmanagement.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
public class VenueController {
    @Autowired
    private VenueService venueService;

    @GetMapping
    public List<Venue> getAllVenues() {
        return venueService.getAllVenues();
    }

    @GetMapping("/{id}")
    public Venue getVenueById(@PathVariable Long id) {
        return venueService.getVenueById(id);
    }

    @PostMapping
    public Venue addVenue(@RequestBody Venue venue) {
        return venueService.addVenue(venue);
    }

    @PutMapping("/{id}")
    public Venue updateVenue(@PathVariable Long id, @RequestBody Venue updatedVenue) {
        return venueService.updateVenue(id, updatedVenue);
    }

    @DeleteMapping("/{id}")
    public void deleteVenue(@PathVariable Long id) {
        venueService.deleteVenue(id);
    }
}
