package com.airtribe.EmployeeTrackingSystem.repository;

import com.airtribe.EmployeeTrackingSystem.entity.Employee;
import com.airtribe.EmployeeTrackingSystem.entity.EmployeeProject;
import com.airtribe.EmployeeTrackingSystem.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
    Optional<EmployeeProject> findByEmployeeAndProject(Employee employee, Project project);
}
