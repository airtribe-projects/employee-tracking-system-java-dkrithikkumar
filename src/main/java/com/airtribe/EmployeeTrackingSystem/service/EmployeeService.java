package com.airtribe.EmployeeTrackingSystem.service;

import com.airtribe.EmployeeTrackingSystem.exception.ExistingRecordFoundException;
import com.airtribe.EmployeeTrackingSystem.model.dto.EmployeeDTO;
import com.airtribe.EmployeeTrackingSystem.model.dto.UpdateEmployeeDTO;
import com.airtribe.EmployeeTrackingSystem.model.response.EmployeeResponseDTO;
import com.airtribe.EmployeeTrackingSystem.entity.Employee;
import com.airtribe.EmployeeTrackingSystem.exception.EmployeeNotFoundException;
import com.airtribe.EmployeeTrackingSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public static Employee toEntity(EmployeeDTO dto) {
        // this method converts the employee dto from the Request Body to Entity object
        Employee employee = new Employee();
        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate joiningDate = LocalDate.parse(dto.getDateOfJoining(), formatter);
        employee.setDateOfJoining(joiningDate);
        return employee;
    }

    public List<EmployeeResponseDTO> getEmployeeList() {
        List<EmployeeResponseDTO> responseDTOs =  employeeRepository.findAll().stream().map(EmployeeResponseDTO::new).toList();
        if(responseDTOs.isEmpty())
            throw new EmployeeNotFoundException("No employee records were found in the system.");
        else
            return responseDTOs;
    }

    public EmployeeResponseDTO findEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent()) {
            return new EmployeeResponseDTO(employee.get());
        } else
            throw new EmployeeNotFoundException("Employee with ID " + employee + " not found");
    }


    public void updateEmployeeDetails(Long employeeId, UpdateEmployeeDTO updatedEmployeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            if (updatedEmployeeDTO.getFullName() != null && !updatedEmployeeDTO.getFullName().isBlank()) {
                employee.setFullName(updatedEmployeeDTO.getFullName());
            }
            if (updatedEmployeeDTO.getEmail() != null && !updatedEmployeeDTO.getEmail().isBlank()) {
                employee.setEmail(updatedEmployeeDTO.getEmail());
            }
            if (updatedEmployeeDTO.getPhoneNumber() != null && !updatedEmployeeDTO.getPhoneNumber().isBlank()) {
                employee.setPhoneNumber(updatedEmployeeDTO.getPhoneNumber());
            }
            if (updatedEmployeeDTO.getDateOfJoining() != null && !updatedEmployeeDTO.getDateOfJoining().isBlank()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate joiningDate = LocalDate.parse(updatedEmployeeDTO.getDateOfJoining().trim(), formatter);
                employee.setDateOfJoining(joiningDate);
            }
            employeeRepository.save(employee);
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found");
        }
    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + employeeId + " not found"));
        employeeRepository.delete(employee);
    }


    public void createNewEmployees(List<EmployeeDTO> employeeDTOs) {
        List<Employee> employees = employeeDTOs.stream()
                .map(EmployeeService::toEntity)
                .toList();

        for (Employee employee : employees) {
            boolean existsByEmail = employeeRepository.existsByEmail(employee.getEmail());
            boolean existsByPhoneNumber = employeeRepository.existsByPhoneNumber(employee.getPhoneNumber());

            if (existsByEmail) {
                throw new ExistingRecordFoundException("Employee with Email: " + employee.getEmail() + " already exists.");
            } else if (existsByPhoneNumber) {
                throw new ExistingRecordFoundException("Employee with Phone Number: " + employee.getPhoneNumber() + " already exists.");
            }
        }

        employeeRepository.saveAll(employees);
    }

}
