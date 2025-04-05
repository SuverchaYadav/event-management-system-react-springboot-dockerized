package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.Budget;
import com.example.eventmanagement.service.BudgetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    // Add a budget entry for an event
    @PostMapping("/add/{eventId}")
    public String addBudget(@PathVariable Long eventId, @RequestBody Budget budget) {
        return budgetService.addBudget(eventId, budget);
    }

    // Get all budget entries for an event
    @GetMapping("/{eventId}")
    public List<Budget> getBudgetByEvent(@PathVariable Long eventId) {
        return budgetService.getBudgetByEvent(eventId);
    }

    // Update expenses for a budget category
    @PutMapping("/update/{budgetId}")
    public String updateBudget(@PathVariable Long budgetId, @RequestParam double newExpenses) {
        return budgetService.updateBudget(budgetId, newExpenses);
    }

    // Delete a budget entry
    @DeleteMapping("/delete/{budgetId}")
    public String deleteBudget(@PathVariable Long budgetId) {
        return budgetService.deleteBudget(budgetId);
    }
}
