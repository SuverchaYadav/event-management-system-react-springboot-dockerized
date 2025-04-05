package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.model.User;
import com.example.eventmanagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000") // Allow frontend requests
@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        // Force initialization of registeredUsers
        if (event != null && event.getRegisteredUsers() != null) {
            event.getRegisteredUsers().size(); // This triggers lazy loading
        }
        return event;
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        return eventService.updateEvent(id, updatedEvent);
    }

    @PostMapping("/register/{userId}/{eventId}")
    public String registerUserForEvent(@PathVariable Long userId, @PathVariable Long eventId) {
        return eventService.registerUserForEvent(userId, eventId);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
