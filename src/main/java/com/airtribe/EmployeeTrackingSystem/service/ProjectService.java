package com.airtribe.EmployeeTrackingSystem.service;

import com.airtribe.EmployeeTrackingSystem.dto.DepartmentDTO;
import com.airtribe.EmployeeTrackingSystem.dto.ProjectDTO;
import com.airtribe.EmployeeTrackingSystem.entity.Department;
import com.airtribe.EmployeeTrackingSystem.entity.Employee;
import com.airtribe.EmployeeTrackingSystem.entity.Project;
import com.airtribe.EmployeeTrackingSystem.exception.EmployeeNotFoundException;
import com.airtribe.EmployeeTrackingSystem.exception.ProjectNotFoundException;
import com.airtribe.EmployeeTrackingSystem.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project toEntity(ProjectDTO dto) {
        Project project = new Project();
        project.setProjectTitle(dto.getProjectTitle());
        project.setProjectDescription(dto.getProjectDescription());
        project.setProjectBudget(dto.getProjectBudget());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(dto.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(dto.getEndDate(), formatter);

        project.setStartDate(startDate);
        project.setEndDate(endDate);

        return project;
    }
    public List<Project> getProjectList() {
        return projectRepository.findAll();
    }

    public Project findProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project with ID " + projectId + " not found"));
    }

    public Project createNewProject(Project project) {
        return projectRepository.save(project);
    }

    public String deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
        return "Project Deleted.";
    }
}
