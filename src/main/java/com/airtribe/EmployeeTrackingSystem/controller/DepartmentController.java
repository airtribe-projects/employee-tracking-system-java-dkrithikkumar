package com.airtribe.EmployeeTrackingSystem.controller;

import com.airtribe.EmployeeTrackingSystem.model.dto.DepartmentDTO;
import com.airtribe.EmployeeTrackingSystem.model.response.DepartmentResponseDTO;
import com.airtribe.EmployeeTrackingSystem.service.DepartmentService;
import com.airtribe.EmployeeTrackingSystem.utils.CreateValidation;
import com.airtribe.EmployeeTrackingSystem.utils.UpdateValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService _departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>>  getAllDepartments() {
        return ResponseEntity.ok( _departmentService.getDepartmentList());
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(@Valid @PathVariable("departmentId") Long departmentId) {
        return ResponseEntity.ok(_departmentService.findDepartmentById(departmentId));
    }

    @PostMapping
    public ResponseEntity<String> saveNewDepartments(@RequestBody @Validated(CreateValidation.class) List<DepartmentDTO> departmentDTOS) {
        _departmentService.createMultipleDepartments(departmentDTOS);
        return ResponseEntity.ok("Departments created successfully");

    }

    @PostMapping("/{departmentId}/employees")
    public ResponseEntity<String> assignEmployeesToDepartment(
            @Valid @PathVariable("departmentId") Long departmentId,
            @Valid @RequestBody List<Long> employeeIds) {

        _departmentService.assignEmployeesToDepartment(departmentId, employeeIds);
        DepartmentResponseDTO responseDTO =  _departmentService.findDepartmentById(departmentId);
        return ResponseEntity.ok("Employees assigned successfully to " + responseDTO.getDepartmentTitle() + " ( departmentId:  " + departmentId + ")");
    }

    @PostMapping("/{departmentId}/projects")
    public ResponseEntity<String> assignProjectsToDepartment(
            @Valid @PathVariable("departmentId") Long departmentId,
            @Valid @RequestBody List<Long> projectIds) {

        _departmentService.assignProjectsToDepartment(departmentId, projectIds);
        DepartmentResponseDTO responseDTO =  _departmentService.findDepartmentById(departmentId);
        return ResponseEntity.ok("Projects assigned successfully to " + responseDTO.getDepartmentTitle() + " (Department ID:  " + departmentId + ")");
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<String> updateDepartmentDetails(
            @PathVariable("departmentId") Long departmentId,
            @RequestBody @Validated(UpdateValidation.class) DepartmentDTO departmentDTO) {
        _departmentService.updateDepartmentDetails(departmentId, departmentDTO);
        return ResponseEntity.ok("Department information updated successfully.");
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("departmentId") Long departmentId) {
        _departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok("Department with ID " + departmentId + " deleted successfully.");
    }

}
