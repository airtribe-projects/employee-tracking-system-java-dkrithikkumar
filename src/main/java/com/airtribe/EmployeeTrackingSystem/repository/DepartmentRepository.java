package com.airtribe.EmployeeTrackingSystem.repository;

import com.airtribe.EmployeeTrackingSystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByDepartmentTitle(String departmentTitle);
}
