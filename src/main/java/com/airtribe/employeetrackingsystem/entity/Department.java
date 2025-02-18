package com.airtribe.EmployeeTrackingSystem.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;
    private String departmentTitle;
    private String departmentDescription;
    private BigDecimal departmentBudget;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Project> projects;

    public Department() {};

    public Department(Long departmentId, String departmentTitle, String departmentDescription, BigDecimal departmentBudget, List<Employee> employees, List<Project> projects) {
        this.departmentId = departmentId;
        this.departmentTitle = departmentTitle;
        this.departmentDescription = departmentDescription;
        this.departmentBudget = departmentBudget;
        this.employees = employees;
        this.projects = projects;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
