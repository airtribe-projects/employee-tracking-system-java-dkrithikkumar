package com.airtribe.EmployeeTrackingSystem.controller;

import com.airtribe.EmployeeTrackingSystem.dto.DepartmentDTO;
import com.airtribe.EmployeeTrackingSystem.entity.Department;
import com.airtribe.EmployeeTrackingSystem.entity.Employee;
import com.airtribe.EmployeeTrackingSystem.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService _departmentService;

    @GetMapping
    public List<Department> getAllDepartments() {
        return _departmentService.getDepartmentList();
    }

    @GetMapping("/{departmentId}")
    public Department getDepartmentById(@Valid @PathVariable("departmentId") Long departmentId) {
        return _departmentService.findDepartmentById(departmentId);
    }

    @PostMapping
    public Department saveNewDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        Department department = _departmentService.toEntity(departmentDTO);
        return _departmentService.createNewDepartment(department);
    }

}
