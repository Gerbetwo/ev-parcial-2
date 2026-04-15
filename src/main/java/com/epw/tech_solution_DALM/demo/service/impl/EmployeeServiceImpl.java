package com.epw.tech_solution_DALM.demo.service.impl;

import com.epw.tech_solution_DALM.demo.dto.CreateEmployeeRequest;
import com.epw.tech_solution_DALM.demo.dto.EmployeeResponse;
import com.epw.tech_solution_DALM.demo.dto.UpdateEmployeeRequest;
import com.epw.tech_solution_DALM.demo.entity.Department;
import com.epw.tech_solution_DALM.demo.entity.Employee;
import com.epw.tech_solution_DALM.demo.exception.ResourceNotFoundException;
import com.epw.tech_solution_DALM.demo.repository.DepartmentRepository;
import com.epw.tech_solution_DALM.demo.repository.EmployeeRepository;
import com.epw.tech_solution_DALM.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeResponse createEmployee(CreateEmployeeRequest employeeRequest) {
        Department department = departmentRepository.findById(employeeRequest.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id: " + employeeRequest.getDepartmentId()
                ));

        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPosition(employeeRequest.getPosition());
        employee.setSalary(employeeRequest.getSalary());
        employee.setHireDate(employeeRequest.getHireDate());
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        return mapToResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, UpdateEmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        Department department = departmentRepository.findById(employeeRequest.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id: " + employeeRequest.getDepartmentId()
                ));

        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPosition(employeeRequest.getPosition());
        employee.setSalary(employeeRequest.getSalary());
        employee.setHireDate(employeeRequest.getHireDate());
        employee.setDepartment(department);

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
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setEmail(employee.getEmail());
        response.setPosition(employee.getPosition());
        response.setSalary(employee.getSalary());
        response.setHireDate(employee.getHireDate());
        response.setDepartmentId(employee.getDepartment().getId());
        response.setDepartmentName(employee.getDepartment().getName());
        return response;
    }
}