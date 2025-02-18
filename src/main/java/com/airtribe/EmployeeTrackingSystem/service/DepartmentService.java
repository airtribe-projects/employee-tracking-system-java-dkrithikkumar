package com.airtribe.EmployeeTrackingSystem.service;

import com.airtribe.EmployeeTrackingSystem.exception.ExistingRecordFoundException;
import com.airtribe.EmployeeTrackingSystem.model.dto.DepartmentDTO;
import com.airtribe.EmployeeTrackingSystem.model.response.DepartmentResponseDTO;
import com.airtribe.EmployeeTrackingSystem.entity.Department;
import com.airtribe.EmployeeTrackingSystem.entity.Employee;
import com.airtribe.EmployeeTrackingSystem.entity.Project;
import com.airtribe.EmployeeTrackingSystem.exception.DepartmentNotFoundException;
import com.airtribe.EmployeeTrackingSystem.exception.EmployeeNotFoundException;
import com.airtribe.EmployeeTrackingSystem.exception.ProjectNotFoundException;
import com.airtribe.EmployeeTrackingSystem.repository.DepartmentRepository;
import com.airtribe.EmployeeTrackingSystem.repository.EmployeeRepository;
import com.airtribe.EmployeeTrackingSystem.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public static Department toEntity(DepartmentDTO dto) {
        Department department = new Department();
        department.setDepartmentTitle(dto.getDepartmentTitle());
        department.setDepartmentDescription(dto.getDepartmentDescription());
        department.setDepartmentBudget(dto.getDepartmentBudget());
        return department;
    }

    public List<DepartmentResponseDTO> getDepartmentList() {
        List<DepartmentResponseDTO> departmentResponseDTOS = departmentRepository.findAll().stream().map(DepartmentResponseDTO::new).toList();
        if(departmentResponseDTOS.isEmpty())
            throw new DepartmentNotFoundException("No department records were found in the system.");
        else
            return departmentResponseDTOS;
    }

    public DepartmentResponseDTO findDepartmentById(Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(department.isPresent())
            return new DepartmentResponseDTO(department.get());
        else
            throw new DepartmentNotFoundException("Department with ID " + departmentId + " not found");
    }


    public void createMultipleDepartments(List<DepartmentDTO> departmentDTOs) {
        List<Department> departments = departmentDTOs.stream()
                .map(DepartmentService::toEntity)
                .toList();

        for (Department department : departments) {
            boolean existsByTitle = departmentRepository.existsByDepartmentTitle(department.getDepartmentTitle());

            if (existsByTitle) {
                throw new ExistingRecordFoundException("Department with Title: " + department.getDepartmentTitle() + " already exists.");
            }
        }
        departmentRepository.saveAll(departments);
    }

    public void updateDepartmentDetails(Long departmentId, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with ID " + departmentId + " not found"));

        if (departmentDTO.getDepartmentTitle() != null && !departmentDTO.getDepartmentTitle().isBlank()) {
            department.setDepartmentTitle(departmentDTO.getDepartmentTitle());
        }
        if (departmentDTO.getDepartmentDescription() != null && !departmentDTO.getDepartmentDescription().isBlank()) {
            department.setDepartmentDescription(departmentDTO.getDepartmentDescription());
        }
        if (departmentDTO.getDepartmentBudget() != null) {
            department.setDepartmentBudget(departmentDTO.getDepartmentBudget());
        }

        departmentRepository.save(department);
    }

    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with ID " + departmentId + " not found"));
        departmentRepository.delete(department);
    }

    @Transactional
    public void assignEmployeesToDepartment(Long departmentId, List<Long> employeeIds) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with ID " + departmentId + " not found"));

        List<Employee> employees = employeeRepository.findAllById(employeeIds);

        if (employees.size() != employeeIds.size()) {
            List<Long> foundIds = employees.stream().map(Employee::getEmployeeId).toList();
            List<Long> missingIds = employeeIds.stream().filter(id -> !foundIds.contains(id)).toList();
            throw new EmployeeNotFoundException("Employees not found with IDs: " + missingIds);
        }

        employees.forEach(employee -> employee.setDepartment(department));
        employeeRepository.saveAll(employees);
        departmentRepository.save(department);
    }

    @Transactional
    public void assignProjectsToDepartment(Long departmentId, List<Long> projectIds) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with ID " + departmentId + " not found"));

        List<Project> projects = projectRepository.findAllById(projectIds);

        if (projects.size() != projectIds.size()) {
            List<Long> foundIds = projects.stream().map(Project::getProjectId).toList();
            List<Long> missingIds = projectIds.stream().filter(id -> !foundIds.contains(id)).toList();
            throw new ProjectNotFoundException("Projects not found with IDs: " + missingIds);
        }

        projects.forEach(project -> project.setDepartment(department));
        projectRepository.saveAll(projects);
        departmentRepository.save(department);
    }
}
