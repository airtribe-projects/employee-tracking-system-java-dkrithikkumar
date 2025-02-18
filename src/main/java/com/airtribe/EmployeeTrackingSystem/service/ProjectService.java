package com.airtribe.EmployeeTrackingSystem.service;

import com.airtribe.EmployeeTrackingSystem.model.EmployeeProjectRole;
import com.airtribe.EmployeeTrackingSystem.model.dto.ProjectDTO;
import com.airtribe.EmployeeTrackingSystem.model.dto.UpdateProjectDTO;
import com.airtribe.EmployeeTrackingSystem.model.response.ProjectResponseDTO;
import com.airtribe.EmployeeTrackingSystem.entity.Employee;
import com.airtribe.EmployeeTrackingSystem.entity.EmployeeProject;
import com.airtribe.EmployeeTrackingSystem.entity.Project;
import com.airtribe.EmployeeTrackingSystem.exception.DuplicateAssignmentException;
import com.airtribe.EmployeeTrackingSystem.exception.EmployeeNotFoundException;
import com.airtribe.EmployeeTrackingSystem.exception.ProjectNotFoundException;
import com.airtribe.EmployeeTrackingSystem.repository.DepartmentRepository;
import com.airtribe.EmployeeTrackingSystem.repository.EmployeeProjectRepository;
import com.airtribe.EmployeeTrackingSystem.repository.EmployeeRepository;
import com.airtribe.EmployeeTrackingSystem.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    public static Project toEntity(ProjectDTO dto) {
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
    public List<ProjectResponseDTO> getProjectList() {
        List<ProjectResponseDTO> responseDTOs =  projectRepository.findAll().stream().map(ProjectResponseDTO::new).toList();
        if(responseDTOs.isEmpty())
            throw new ProjectNotFoundException("No project records were found in the system.");
        else
            return responseDTOs;
    }

    public ProjectResponseDTO findProjectById(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if(project.isPresent()) {
            return new ProjectResponseDTO(project.get());
        } else
            throw new ProjectNotFoundException("Project with ID " + projectId + " not found");
    }


    public void createMultipleProjects(List<ProjectDTO> projectDTOs) {
        List<Project> projects = projectDTOs.stream().map(ProjectService::toEntity).toList();
        projectRepository.saveAll(projects);
    }

    public void updateProjectDetails(Long projectId, UpdateProjectDTO updatedProjectDTO) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + projectId + " not found"));

        if (updatedProjectDTO.getProjectTitle() != null && !updatedProjectDTO.getProjectTitle().isBlank()) {
            project.setProjectTitle(updatedProjectDTO.getProjectTitle());
        }
        if (updatedProjectDTO.getProjectDescription() != null && !updatedProjectDTO.getProjectDescription().isBlank()) {
            project.setProjectDescription(updatedProjectDTO.getProjectDescription());
        }
        if (updatedProjectDTO.getProjectBudget() != null) {
            project.setProjectBudget(updatedProjectDTO.getProjectBudget());
        }
        if (updatedProjectDTO.getStartDate() != null && !updatedProjectDTO.getStartDate().isBlank()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(updatedProjectDTO.getStartDate().trim(), formatter);
            project.setStartDate(startDate);
        }
        if (updatedProjectDTO.getEndDate() != null && !updatedProjectDTO.getEndDate().isBlank()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate endDate = LocalDate.parse(updatedProjectDTO.getEndDate().trim(), formatter);
            project.setEndDate(endDate);
        }

        projectRepository.save(project);
    }




    public void deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + projectId + " not found"));

        projectRepository.delete(project);
    }

    @Transactional
    public void assignEmployeesToProject(Long projectId, List<EmployeeProjectRole> employeeProjectRoleList) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + projectId + " not found"));

        // Extract employeeIds from the input list
        List<Long> employeeIds = employeeProjectRoleList.stream()
                .map(EmployeeProjectRole::getEmployeeId)
                .collect(Collectors.toList());

        List<Employee> employees = employeeRepository.findAllById(employeeIds);

        // Create a set of found employee IDs for fast lookup
        Set<Long> foundIds = employees.stream()
                .map(Employee::getEmployeeId)
                .collect(Collectors.toSet());

        List<Long> missingIds = employeeIds.stream()
                .filter(id -> !foundIds.contains(id))
                .toList();

        if (!missingIds.isEmpty()) {
            throw new EmployeeNotFoundException("Employees not found with IDs: " + missingIds);
        }

        // Create EmployeeProject mappings and ensure no employee has multiple roles in a project
        List<EmployeeProject> newAssignments = new ArrayList<>();
        for (EmployeeProjectRole employeeProjectRole : employeeProjectRoleList) {
            Employee employee = employees.stream()
                    .filter(e -> e.getEmployeeId().equals(employeeProjectRole.getEmployeeId()))
                    .findFirst()
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + employeeProjectRole.getEmployeeId() + " not found"));

            // Check if the employee is already assigned to the project, regardless of role
            Optional<EmployeeProject> existingAssignment = employeeProjectRepository
                    .findByEmployeeAndProject(employee, project);

            if (existingAssignment.isPresent()) {
                throw new DuplicateAssignmentException("Employee " + employee.getEmployeeId() +
                        " is already assigned to project " + project.getProjectId() + " and cannot have multiple roles.");
            }

            newAssignments.add(new EmployeeProject(employee, project, employeeProjectRole.getProjectRole()));
        }

        employeeProjectRepository.saveAll(newAssignments);
    }


}
