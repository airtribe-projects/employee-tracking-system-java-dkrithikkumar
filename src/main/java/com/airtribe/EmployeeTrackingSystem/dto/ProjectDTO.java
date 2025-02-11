package com.airtribe.EmployeeTrackingSystem.dto;

import com.airtribe.EmployeeTrackingSystem.utils.ValidDateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ProjectDTO {
    @NotBlank(message = "Project Title should not be empty")
    @Size(max = 25, message = "Project Title should not be more than 25 characters")
    private String projectTitle;
    @Size(max = 50, message = "Project Description should not be more than 50 characters")
    private String projectDescription;
    @NotNull(message = "Project Budget should not be empty")
    private Long projectBudget;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Start Date of Project should not be empty")
    @ValidDateFormat
    private String startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "End Date of Project should not be empty")
    @ValidDateFormat
    private String endDate;

    public ProjectDTO() {}

    public ProjectDTO(String projectTitle, String projectDescription, Long projectBudget, String startDate, String endDate) {
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

    public Long getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(Long projectBudget) {
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
