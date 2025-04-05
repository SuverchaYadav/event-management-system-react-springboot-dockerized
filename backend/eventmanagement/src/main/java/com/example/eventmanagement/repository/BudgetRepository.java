package com.example.eventmanagement.repository;

import com.example.eventmanagement.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByEventId(Long eventId);
}
