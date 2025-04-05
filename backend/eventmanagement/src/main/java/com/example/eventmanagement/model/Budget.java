package com.example.eventmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Getter
@Setter
@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @JsonBackReference  // Prevents recursion
    private Event event;

    @Column(nullable = false)
    private double totalBudget;  // Total event budget

    @Column(nullable = false)
    private Double allocatedBudget = 0.0;  // Set default value

    @Column(nullable = false)
    private double expenses;  // Current expenses

    @Column(nullable = false)
    private double remainingBudget;  // Remaining budget

    @Column(nullable = false)
    private String category;  // e.g., Venue, Food, Decorations

    @Column(nullable = false)
    private LocalDate date;  // Budget entry date
}
