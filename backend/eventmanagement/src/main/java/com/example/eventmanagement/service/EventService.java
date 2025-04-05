package com.example.eventmanagement.service;

import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.model.User;
import com.example.eventmanagement.repository.EventRepository;
import com.example.eventmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public List<Event> getEventsByCategory(String category) {
        return eventRepository.findByCategory(category);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Optional<Event> existingEventOpt = eventRepository.findById(id);
        if (existingEventOpt.isPresent()) {
            Event existingEvent = existingEventOpt.get();
            existingEvent.setName(updatedEvent.getName());
            existingEvent.setCategory(updatedEvent.getCategory());
            existingEvent.setLocation(updatedEvent.getLocation());
            existingEvent.setDate(updatedEvent.getDate());
            existingEvent.setMaxParticipants(updatedEvent.getMaxParticipants());
            return eventRepository.save(existingEvent);
        }
        return null;
    }

    public String registerUserForEvent(Long userId, Long eventId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Event> eventOpt = eventRepository.findById(eventId);

        if (userOpt.isEmpty() || eventOpt.isEmpty()) {
            return "User or event not found!";
        }

        User user = userOpt.get();
        Event event = eventOpt.get();

        if (event.getRegisteredUsers().contains(user)) {
            return "User already registered for this event!";
        }

        if (event.getRegisteredUsers().size() >= event.getMaxParticipants()) {
            return "Event is full!";
        }

        user.getRegisteredEvents().add(event);
        event.getRegisteredUsers().add(user);

        userRepository.save(user);
        eventRepository.save(event);

        return "User registered successfully!";
    }


//    public void deleteEvent(Long id) {
//        eventRepository.findById(id).ifPresent(event -> {
//            // Cascade will handle booking deletion automatically
//            eventRepository.delete(event);
//        });
//    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

}
