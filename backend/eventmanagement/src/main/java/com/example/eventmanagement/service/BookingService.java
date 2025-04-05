package com.example.eventmanagement.service;

import com.example.eventmanagement.model.Booking;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.model.User;
import com.example.eventmanagement.repository.BookingRepository;
import com.example.eventmanagement.repository.EventRepository;
import com.example.eventmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public String bookEvent(Long userId, Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalEvent.isPresent() && optionalUser.isPresent()) {
            Event event = optionalEvent.get();
            User user = optionalUser.get();

            // Check if the user is already registered
            if (event.getRegisteredUsers().contains(user)) {
                return "User already registered for this event!";
            }

            // Check if event has space
            if (event.getRegisteredUsers().size() < event.getMaxParticipants()) {
                // Add user to registeredUsers set
                event.getRegisteredUsers().add(user);

                // 🔥 Update the registered_users count in the event table
                event.setRegisteredUsersCount(event.getRegisteredUsers().size());

                // Save the updated event
                eventRepository.save(event);

                return "Event booked successfully!";
            } else {
                return "Event is full!";
            }
        }
        return "User or Event not found!";
    }


    public String cancelBooking(Long userId, Long eventId) {
        Optional<Booking> bookingOpt = bookingRepository.findByUserIdAndEventId(userId, eventId);
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (bookingOpt.isPresent() && optionalEvent.isPresent() && optionalUser.isPresent()) {
            Event event = optionalEvent.get();
            User user = optionalUser.get();

            // Remove user from registeredUsers set
            event.getRegisteredUsers().remove(user);

            // Update the registered_users count
            event.setRegisteredUsersCount(event.getRegisteredUsers().size());

            // Save updated event
            eventRepository.save(event);

            // Delete the booking record
            bookingRepository.delete(bookingOpt.get());

            return "Booking canceled successfully!";
        }
        return "Booking not found!";
    }

}
