package com.airtribe.EmployeeTrackingSystem.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class DepartmentDTO {
    @NotBlank(message = "Department Title should not be empty")
    @Size(max = 35, message = "Department Title should not be more than 35 characters")
    private String departmentTitle;
    @Size(max = 80, message = "Department Description should not be more than 80 characters")
    private String departmentDescription;
    @NotNull(message = "Department Budget should not be empty")
    private BigDecimal departmentBudget;

    public DepartmentDTO() {}

    public DepartmentDTO(String departmentTitle, String departmentDescription, BigDecimal departmentBudget) {
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

    public BigDecimal getDepartmentBudget() {
        return departmentBudget;
    }

    public void setDepartmentBudget(BigDecimal departmentBudget) {
        this.departmentBudget = departmentBudget;
    }
}
