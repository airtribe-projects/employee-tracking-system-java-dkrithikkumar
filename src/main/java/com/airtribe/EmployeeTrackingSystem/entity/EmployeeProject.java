package com.airtribe.EmployeeTrackingSystem.entity;

import jakarta.persistence.*;

// This entity stores the relationship between the employee and projects and the role given to each employee within the project
@Entity
@Table(name = "employee_project", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"employee_id", "project_id"})
})
public class EmployeeProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeProjectMapId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String projectRole;

    public EmployeeProject() {}

    public EmployeeProject(Long employeeProjectMapId, Employee employee, Project project, String projectRole) {
        this.employeeProjectMapId = employeeProjectMapId;
        this.employee = employee;
        this.project = project;
        this.projectRole = projectRole;
    }

    public EmployeeProject(Employee employee, Project project, String projectRole) {
        this.employee = employee;
        this.project = project;
        this.projectRole = projectRole;
    }

    public Long getEmployeeProjectMapId() {
        return employeeProjectMapId;
    }

    public void setEmployeeProjectMapId(Long employeeProjectMapId) {
        this.employeeProjectMapId = employeeProjectMapId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }
}

