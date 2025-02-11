package com.airtribe.EmployeeTrackingSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DepartmentDTO {
    @NotBlank(message = "Department Title should not be empty")
    @Size(max = 25, message = "Department Title should not be more than 25 characters")
    private String departmentTitle;
    @Size(max = 50, message = "Department Description should not be more than 50 characters")
    private String departmentDescription;
    @NotNull(message = "Department Budget should not be empty")
    private Long departmentBudget;

    public DepartmentDTO() {}

    public DepartmentDTO(String departmentTitle, String departmentDescription, Long departmentBudget) {
        this.departmentTitle = departmentTitle;
        this.departmentDescription = departmentDescription;
        this.departmentBudget = departmentBudget;
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

    public Long getDepartmentBudget() {
        return departmentBudget;
    }

    public void setDepartmentBudget(Long departmentBudget) {
        this.departmentBudget = departmentBudget;
    }
}
