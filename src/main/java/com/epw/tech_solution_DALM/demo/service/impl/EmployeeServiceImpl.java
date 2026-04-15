package com.epw.tech_solution_DALM.demo.service.impl;

import com.epw.tech_solution_DALM.demo.dto.CreateEmployeeRequest;
import com.epw.tech_solution_DALM.demo.dto.EmployeeResponse;
import com.epw.tech_solution_DALM.demo.dto.UpdateEmployeeRequest;
import com.epw.tech_solution_DALM.demo.entity.Employee;
import com.epw.tech_solution_DALM.demo.exception.ResourceNotFoundException;
import com.epw.tech_solution_DALM.demo.repository.EmployeeRepository;
import com.epw.tech_solution_DALM.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponse createEmployee(CreateEmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPosition(employeeRequest.getPosition());
        employee.setSalary(employeeRequest.getSalary());
        employee.setHireDate(employeeRequest.getHireDate());

        Employee savedEmployee = employeeRepository.save(employee);

        return mapToResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, UpdateEmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPosition(employeeRequest.getPosition());
        employee.setSalary(employeeRequest.getSalary());
        employee.setHireDate(employeeRequest.getHireDate());

        Employee updatedEmployee = employeeRepository.save(employee);

        return mapToResponse(updatedEmployee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPosition(),
                employee.getSalary(),
                employee.getHireDate()
        );
    }
}