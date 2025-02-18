package com.airtribe.EmployeeTrackingSystem.model.response;

import com.airtribe.EmployeeTrackingSystem.model.summary.DepartmentSummary;
import com.airtribe.EmployeeTrackingSystem.model.summary.ProjectSummary;
import com.airtribe.EmployeeTrackingSystem.entity.Employee;


import java.util.List;
import java.util.stream.Collectors;

public class EmployeeResponseDTO {
    private Long employeeId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private DepartmentSummary department;
    private List<ProjectSummary> projects;

    public  EmployeeResponseDTO() {}
    public EmployeeResponseDTO(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.fullName = employee.getFullName();
        this.email = employee.getEmail();
        this.phoneNumber = employee.getPhoneNumber();
        if (employee.getDepartment() != null) {
            this.department = new DepartmentSummary(employee.getDepartment().getDepartmentId(), employee.getDepartment().getDepartmentTitle());
        } else {
            this.department = null;
        }
        this.projects = employee.getProjects() != null ? employee.getProjects().stream()
                .map(project -> new ProjectSummary(project.getProject().getProjectId(), project.getProject().getProjectTitle(), project.getProjectRole()))
                .collect(Collectors.toList())
                : List.of();
    }


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public DepartmentSummary getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentSummary department) {
        this.department = department;
    }

    public List<ProjectSummary> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectSummary> projects) {
        this.projects = projects;
    }
}
