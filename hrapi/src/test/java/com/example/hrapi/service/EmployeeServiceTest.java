package com.example.hrapi.service;

import com.example.hrapi.model.Employee;
import com.example.hrapi.model.SparseMatrix;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getOrgchart() {
        Employee topManager = employeeService.getOrgchart();
        assertNotNull(topManager);
        assertEquals("Jamie", topManager.getName());
    }

    @Test
    void traverseOrgchart() {
        Employee topManager = employeeService.getOrgchart();
        SparseMatrix matrix = employeeService.traverseOrgchart(topManager);
        assertEquals(2, matrix.getMaxCol());
        assertEquals(5,matrix.getMaxRow());
    }
}
