package com.example.hrapi.service;

import com.example.hrapi.exception.ResourceNotFoundException;
import com.example.hrapi.model.Employee;
import com.example.hrapi.model.SparseMatrix;
import com.example.hrapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return employee;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employeeRepository.delete(employee);
    }

    public Employee getOrgchart() {
        return buildOrgchart(employeeRepository.findAll());

    }

    public SparseMatrix getOrgchartMatrix() {
        Employee topManager = buildOrgchart(employeeRepository.findAll());
        return traverseOrgchart(topManager);
    }

    public Employee buildOrgchart(List<Employee> employees) {
        Employee topManager;
        Map<Integer, Employee> maps = employees.stream()
                .collect(Collectors.toMap(Employee::getEmployeeID, employee -> employee));
        employees.stream().forEach(employee -> {
            if (employee.getManagerID() != null) {
                Employee manager = maps.get(employee.getManagerID());
                if (manager != null) {
                    manager.addSubordinate(employee);
                }
            }
        });
        topManager = employees.stream()
                .filter(employee -> employee.getManagerID() == null && employee.getSubordinates().size() > 0)
                .findFirst()
                .orElse(null);
        return topManager;
    }

    private void traverseOrgchart(Employee employee, int level, SparseMatrix maxtrix) {
        if (employee == null) return;
        maxtrix.add(maxtrix.getElementNumber(), level, employee.getName());
        List<Employee> subordinates = employee.getSubordinates();
        if (subordinates != null) {
            subordinates.forEach(subordinate -> traverseOrgchart(subordinate, level + 1, maxtrix));
        }
    }

    public SparseMatrix traverseOrgchart(Employee employee) {
        SparseMatrix<String> matrix = new SparseMatrix();
        traverseOrgchart(employee, 0, matrix);
        return matrix;
    }
}
