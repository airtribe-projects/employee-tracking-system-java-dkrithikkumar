package com.airtribe.EmployeeTrackingSystem.controller;

import com.airtribe.EmployeeTrackingSystem.dto.EmployeeDTO;
import com.airtribe.EmployeeTrackingSystem.entity.Employee;
import com.airtribe.EmployeeTrackingSystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService _employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return _employeeService.getEmployeeList();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@Valid @PathVariable("employeeId") Long employeeId) {
        return _employeeService.findEmployeeById(employeeId);
    }

    @PostMapping
    public Employee saveNewEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = _employeeService.toEntity(employeeDTO);

        return _employeeService.createNewEmployee(employee);
    }

}
