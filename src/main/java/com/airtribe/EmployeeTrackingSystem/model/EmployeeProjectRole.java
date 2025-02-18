package com.airtribe.EmployeeTrackingSystem.model;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmployeeProjectRole {
    @NotNull(message = "Employee ID cannot be empty")
    private Long employeeId;
    @NotEmpty(message = "The project role cannot be empty")
    private String projectRole;

    public EmployeeProjectRole(Long employeeId, String projectRole) {
        this.employeeId = employeeId;
        this.projectRole = projectRole;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }
}
