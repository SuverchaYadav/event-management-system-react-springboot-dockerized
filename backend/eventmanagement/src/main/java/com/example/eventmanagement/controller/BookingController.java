package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.Booking;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.model.User;
import com.example.eventmanagement.repository.BookingRepository;
import com.example.eventmanagement.repository.EventRepository;
import com.example.eventmanagement.repository.UserRepository;
import com.example.eventmanagement.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // BookingController.java
    @PostMapping("/book/{userId}/{eventId}")
    public ResponseEntity<String> bookEvent(@PathVariable Long userId, @PathVariable Long eventId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new RuntimeException("Event not found"));

            if (event.getRegisteredUsersCount() >= event.getMaxParticipants()) {
                return ResponseEntity.badRequest().body("Event is already full.");
            }

            if (bookingRepository.existsByUserAndEvent(user, event)) {
                return ResponseEntity.badRequest().body("You are already registered for this event.");
            }

            // Create new booking
            Booking newBooking = new Booking();
            newBooking.setUser(user);
            newBooking.setEvent(event);
            bookingRepository.save(newBooking);

            // Add user to event's registered users
            event.getRegisteredUsers().add(user);
            //event.setRegisteredUsersCount(event.getRegisteredUsersCount() + 1);
            event.setRegisteredUsersCount(event.getRegisteredUsers().size());
            eventRepository.save(event);

            // Add event to user's registered events
            user.getRegisteredEvents().add(event);
            userRepository.save(user);

            return ResponseEntity.ok("Successfully registered for the event.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Booking failed: " + e.getMessage());
        }
    }


    @DeleteMapping("/cancel/{userId}/{eventId}")
    public String cancelBooking(@PathVariable Long userId, @PathVariable Long eventId) {
        return bookingService.cancelBooking(userId, eventId);
    }
}
