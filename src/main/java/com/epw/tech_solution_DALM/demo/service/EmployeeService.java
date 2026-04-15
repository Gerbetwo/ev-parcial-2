package com.epw.tech_solution_DALM.demo.service;

import com.epw.tech_solution_DALM.demo.dto.CreateEmployeeRequest;
import com.epw.tech_solution_DALM.demo.dto.UpdateEmployeeRequest;
import com.epw.tech_solution_DALM.demo.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(CreateEmployeeRequest employeeRequest);

    EmployeeResponse updateEmployee(Long id, UpdateEmployeeRequest employeeRequest);

    List<EmployeeResponse> getAllEmployees();
}