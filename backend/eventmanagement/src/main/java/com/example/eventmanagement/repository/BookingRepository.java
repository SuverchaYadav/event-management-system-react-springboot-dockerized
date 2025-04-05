package com.example.eventmanagement.repository;

import com.example.eventmanagement.model.Booking;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByUserAndEvent(User user, Event event);
    Optional<Booking> findByUserIdAndEventId(Long userId, Long eventId);
}
