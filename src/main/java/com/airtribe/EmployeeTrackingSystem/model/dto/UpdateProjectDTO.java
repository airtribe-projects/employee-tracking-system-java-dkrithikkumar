package com.airtribe.EmployeeTrackingSystem.model.dto;

import com.airtribe.EmployeeTrackingSystem.utils.CreateValidation;
import com.airtribe.EmployeeTrackingSystem.utils.NullableNotBlankDateFormat;
import com.airtribe.EmployeeTrackingSystem.utils.UpdateValidation;
import com.airtribe.EmployeeTrackingSystem.utils.ValidDateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class UpdateProjectDTO {
    @Size(max = 35, message = "Project Title should not be more than 35 characters")
    private String projectTitle;
    @Size(max = 80, message = "Project Description should not be more than 80 characters")
    private String projectDescription;
    private BigDecimal projectBudget;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NullableNotBlankDateFormat
    private String startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NullableNotBlankDateFormat
    private String endDate;

    public UpdateProjectDTO(String projectTitle, String projectDescription, BigDecimal projectBudget, String startDate, String endDate) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.projectBudget = projectBudget;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
