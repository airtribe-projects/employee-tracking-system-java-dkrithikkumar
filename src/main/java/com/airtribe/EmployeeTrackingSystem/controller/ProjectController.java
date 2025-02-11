package com.airtribe.EmployeeTrackingSystem.controller;

import com.airtribe.EmployeeTrackingSystem.dto.ProjectDTO;
import com.airtribe.EmployeeTrackingSystem.entity.Department;
import com.airtribe.EmployeeTrackingSystem.entity.Project;
import com.airtribe.EmployeeTrackingSystem.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService _projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return _projectService.getProjectList();
    }

    @GetMapping("/{projectId}")
    public Project getProjectById(@PathVariable("projectId") Long projectId) {
        return _projectService.findProjectById(projectId);
    }

    @PostMapping
    public Project saveNewProject(@Valid @RequestBody ProjectDTO projectDTO) {
        Project project = _projectService.toEntity(projectDTO);
        return _projectService.createNewProject(project);
    }
}
