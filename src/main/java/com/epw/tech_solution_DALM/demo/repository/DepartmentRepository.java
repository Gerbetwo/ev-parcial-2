package com.epw.tech_solution_DALM.demo.repository;

import com.epw.tech_solution_DALM.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}