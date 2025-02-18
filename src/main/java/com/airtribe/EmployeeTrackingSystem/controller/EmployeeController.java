package com.airtribe.EmployeeTrackingSystem.controller;

import com.airtribe.EmployeeTrackingSystem.model.dto.EmployeeDTO;
import com.airtribe.EmployeeTrackingSystem.model.dto.UpdateEmployeeDTO;
import com.airtribe.EmployeeTrackingSystem.model.response.EmployeeResponseDTO;
import com.airtribe.EmployeeTrackingSystem.service.EmployeeService;
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
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService _employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(_employeeService.getEmployeeList());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        return ResponseEntity.ok(_employeeService.findEmployeeById(employeeId));
    }

    @PostMapping
    public ResponseEntity<String> saveNewEmployees(
            @RequestBody @Validated(CreateValidation.class) List<EmployeeDTO> employeeDTOlist) {
        _employeeService.createNewEmployees(employeeDTOlist);
        return ResponseEntity.ok("Employees created successfully");
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<String> updateEmployeeDetails(
            @PathVariable("employeeId") Long employeeId,
            @RequestBody @Valid UpdateEmployeeDTO employeeDTO) {
        _employeeService.updateEmployeeDetails(employeeId, employeeDTO);
        return ResponseEntity.ok("Employee information updated successfully.");
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        _employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee with ID " + employeeId + " deleted successfully.");
    }


}
