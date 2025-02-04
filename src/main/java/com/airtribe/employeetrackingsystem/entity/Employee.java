package com.airtribe.EmployeeTrackingSystem.entity;

import com.airtribe.EmployeeTrackingSystem.utils.Roles;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    private String fullName;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Roles role;

    private LocalDate dateOfJoining;

}
