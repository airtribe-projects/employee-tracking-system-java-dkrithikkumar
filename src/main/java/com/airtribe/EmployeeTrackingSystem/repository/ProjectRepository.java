package com.airtribe.EmployeeTrackingSystem.repository;

import com.airtribe.EmployeeTrackingSystem.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
