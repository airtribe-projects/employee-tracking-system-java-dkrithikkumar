package com.airtribe.EmployeeTrackingSystem.controller;

import com.airtribe.EmployeeTrackingSystem.model.EmployeeProjectRole;
import com.airtribe.EmployeeTrackingSystem.model.dto.ProjectDTO;
import com.airtribe.EmployeeTrackingSystem.model.dto.UpdateProjectDTO;
import com.airtribe.EmployeeTrackingSystem.model.response.ProjectResponseDTO;
import com.airtribe.EmployeeTrackingSystem.service.ProjectService;
import com.airtribe.EmployeeTrackingSystem.utils.CreateValidation;
import com.airtribe.EmployeeTrackingSystem.utils.UpdateValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService _projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {
        return ResponseEntity.ok(_projectService.getProjectList());
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable("projectId") Long projectId) {
        return  ResponseEntity.ok(_projectService.findProjectById(projectId));
    }

    @PostMapping
    public ResponseEntity<String> saveNewProjects(@RequestBody @Validated(CreateValidation.class) List<@Valid ProjectDTO> projectDTOS) {
        _projectService.createMultipleProjects(projectDTOS);
        return ResponseEntity.ok("Projects created successfully");
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<String> updateProjectDetails(
            @PathVariable("projectId") Long projectId,
            @RequestBody @Valid UpdateProjectDTO projectDTO) {
        _projectService.updateProjectDetails(projectId, projectDTO);
        return ResponseEntity.ok("Project information updated successfully.");
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable("projectId") Long projectId) {
        _projectService.deleteProject(projectId);
        return ResponseEntity.ok("Project with ID " + projectId + " deleted successfully.");
    }

    @PostMapping("/{projectId}/employees")
    public ResponseEntity<String> assignEmployeesToProject(
            @PathVariable("projectId") Long projectId,
            @RequestBody List<@Valid EmployeeProjectRole> employeeProjectRoleList) {

        _projectService.assignEmployeesToProject(projectId, employeeProjectRoleList);
        ProjectResponseDTO responseDTO = _projectService.findProjectById(projectId);
        return ResponseEntity.ok("Employees assigned successfully to " + responseDTO.getProjectTitle() + " (Project ID:  " + projectId + ")");
    }
}
