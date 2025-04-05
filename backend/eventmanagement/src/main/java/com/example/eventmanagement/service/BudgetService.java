package com.example.eventmanagement.service;

import com.example.eventmanagement.model.Budget;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.repository.BudgetRepository;
import com.example.eventmanagement.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final EventRepository eventRepository;

    public BudgetService(BudgetRepository budgetRepository, EventRepository eventRepository) {
        this.budgetRepository = budgetRepository;
        this.eventRepository = eventRepository;
    }

    // Add budget for an event
    public String addBudget(Long eventId, Budget budget) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            budget.setEvent(optionalEvent.get());
            budget.setRemainingBudget(budget.getTotalBudget() - budget.getExpenses());
            budgetRepository.save(budget);
            return "Budget added successfully!";
        }
        return "Event not found!";
    }

    // Get budget breakdown for an event
    public List<Budget> getBudgetByEvent(Long eventId) {
        return budgetRepository.findByEventId(eventId);
    }

    // Update expenses in a budget category
    public String updateBudget(Long budgetId, double newExpenses) {
        Optional<Budget> optionalBudget = budgetRepository.findById(budgetId);
        if (optionalBudget.isPresent()) {
            Budget budget = optionalBudget.get();
            budget.setExpenses(newExpenses);
            budget.setRemainingBudget(budget.getTotalBudget() - newExpenses);
            budgetRepository.save(budget);
            return "Budget updated successfully!";
        }
        return "Budget entry not found!";
    }

    // Delete a budget entry
    public String deleteBudget(Long budgetId) {
        if (budgetRepository.existsById(budgetId)) {
            budgetRepository.deleteById(budgetId);
            return "Budget entry deleted successfully!";
        }
        return "Budget entry not found!";
    }
}
