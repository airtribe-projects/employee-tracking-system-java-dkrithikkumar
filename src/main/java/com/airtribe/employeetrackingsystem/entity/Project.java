package com.airtribe.EmployeeTrackingSystem.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
    private String projectTitle;
    private String projectDescription;
    private Long projectBudget;
    private LocalDate startDate;
    private LocalDate endDate;
    @JsonProperty("isProjectActive")
    private boolean isProjectActive;
    @ManyToOne
    private Department department;
    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees;

    public Project() {}

    public Project(Long projectId, String projectTitle, String projectDescription, Long projectBudget, LocalDate startDate, LocalDate endDate, boolean isProjectActive, Department department, List<Employee> employees) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.projectBudget = projectBudget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isProjectActive = isProjectActive;
        this.department = department;
        this.employees = employees;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Long getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(Long projectBudget) {
        this.projectBudget = projectBudget;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isProjectActive() {
        return isProjectActive;
    }

    public void setProjectActive(boolean projectActive) {
        isProjectActive = projectActive;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
