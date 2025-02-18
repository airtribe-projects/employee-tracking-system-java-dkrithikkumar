package com.airtribe.EmployeeTrackingSystem.repository;

import com.airtribe.EmployeeTrackingSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
        boolean existsByEmail(String email);
        boolean existsByPhoneNumber(String phoneNumber);
}
