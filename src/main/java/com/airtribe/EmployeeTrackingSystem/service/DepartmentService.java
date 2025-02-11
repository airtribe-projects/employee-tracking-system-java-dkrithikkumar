package com.airtribe.EmployeeTrackingSystem.service;

import com.airtribe.EmployeeTrackingSystem.dto.DepartmentDTO;
import com.airtribe.EmployeeTrackingSystem.dto.EmployeeDTO;
import com.airtribe.EmployeeTrackingSystem.entity.Department;
import com.airtribe.EmployeeTrackingSystem.entity.Employee;
import com.airtribe.EmployeeTrackingSystem.exception.DepartmentNotFoundException;
import com.airtribe.EmployeeTrackingSystem.exception.ProjectNotFoundException;
import com.airtribe.EmployeeTrackingSystem.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department toEntity(DepartmentDTO dto) {
        Department department = new Department();
        department.setDepartmentTitle(dto.getDepartmentTitle());
        department.setDepartmentDescription(dto.getDepartmentDescription());
        department.setDepartmentBudget(dto.getDepartmentBudget());
        return department;
    }

    public List<Department> getDepartmentList() {
        return departmentRepository.findAll();
    }

    public Department findDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException("Department with ID " + departmentId + " not found"));
    }

    public Department createNewDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
