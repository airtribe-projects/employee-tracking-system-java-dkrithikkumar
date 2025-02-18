package com.airtribe.EmployeeTrackingSystem.model.response;
import com.airtribe.EmployeeTrackingSystem.model.summary.DeptEmployeeSummary;
import com.airtribe.EmployeeTrackingSystem.model.summary.DeptProjectSummary;
import com.airtribe.EmployeeTrackingSystem.entity.Department;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentResponseDTO {
    private Long departmentId;
    private String departmentTitle;
    private String departmentDescription;
    private BigDecimal departmentBudget;
    private List<DeptEmployeeSummary> employees;
    private List<DeptProjectSummary> projects;


    public DepartmentResponseDTO() {}

    public DepartmentResponseDTO(Department department) {
        this.departmentId = department.getDepartmentId();
        this.departmentTitle = department.getDepartmentTitle();
        this.departmentDescription = department.getDepartmentDescription();
        this.departmentBudget = department.getDepartmentBudget();
        this.employees = department.getEmployees().stream()
                .map(emp -> new DeptEmployeeSummary(emp.getEmployeeId(), emp.getFullName()))
                .collect(Collectors.toList());
        this.projects = department.getProjects().stream()
                .map(project -> new DeptProjectSummary(project.getProjectId(), project.getProjectTitle()))
                .collect(Collectors.toList());
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public void setDepartmentTitle(String departmentTitle) {
        this.departmentTitle = departmentTitle;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public BigDecimal getDepartmentBudget() {
        return departmentBudget;
    }

    public void setDepartmentBudget(BigDecimal departmentBudget) {
        this.departmentBudget = departmentBudget;
    }

    public List<DeptEmployeeSummary> getEmployees() {
        return employees;
    }

    public void setEmployees(List<DeptEmployeeSummary> employees) {
        this.employees = employees;
    }

    public List<DeptProjectSummary> getProjects() {
        return projects;
    }

    public void setProjects(List<DeptProjectSummary> projects) {
        this.projects = projects;
    }
}
