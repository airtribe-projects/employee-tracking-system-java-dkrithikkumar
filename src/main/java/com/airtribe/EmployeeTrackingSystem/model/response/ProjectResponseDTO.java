package com.airtribe.EmployeeTrackingSystem.model.response;


import com.airtribe.EmployeeTrackingSystem.model.summary.DepartmentSummary;
import com.airtribe.EmployeeTrackingSystem.model.summary.EmployeeSummary;
import com.airtribe.EmployeeTrackingSystem.entity.Project;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectResponseDTO {

    private Long projectId;
    private String projectTitle;
    private String projectDescription;
    private BigDecimal projectBudget;
    private LocalDate startDate;
    private LocalDate endDate;
    private DepartmentSummary department;
    private List<EmployeeSummary> employees;

    public ProjectResponseDTO(Project project) {
        this.projectId = project.getProjectId();
        this.projectTitle = project.getProjectTitle();
        this.projectDescription = project.getProjectDescription();
        this.projectBudget = project.getProjectBudget();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        if (project.getDepartment() != null) {
            this.department = new DepartmentSummary(project.getDepartment().getDepartmentId(), project.getDepartment().getDepartmentTitle());
        } else {
            this.department = null;
        }
        this.employees = project.getEmployees().stream()
                .map(emp -> new EmployeeSummary(emp.getEmployee().getEmployeeId(), emp.getEmployee().getFullName(), emp.getProjectRole()))
                .collect(Collectors.toList());
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

    public BigDecimal getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(BigDecimal projectBudget) {
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

    public List<EmployeeSummary> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeSummary> employees) {
        this.employees = employees;
    }

    public DepartmentSummary getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentSummary department) {
        this.department = department;
    }
}
