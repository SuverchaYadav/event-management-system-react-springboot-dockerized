package com.example.eventmanagement.repository;

import com.example.eventmanagement.model.Event;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
//    List<Event> findByCategory(String category);
//    Optional<Event> findById(Long id);
    @EntityGraph(attributePaths = {"registeredUsers"})
    List<Event> findAll();

    @EntityGraph(attributePaths = {"registeredUsers"})
    Optional<Event> findById(Long id);

    List<Event> findByCategory(String category);
}
