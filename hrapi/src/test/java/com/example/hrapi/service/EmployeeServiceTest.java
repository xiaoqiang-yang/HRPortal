package com.example.hrapi.service;

import com.example.hrapi.model.Employee;
import com.example.hrapi.model.SparseMatrix;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;
    List<Employee> employeeList;

    @BeforeEach
    void setUp() {
        employeeList = Stream.of(
                new Employee(100, "Alan", 150),
                new Employee(200, "Martin", 100),
                new Employee(150, "Jamie", null),
                new Employee(275, "Alex", 100),
                new Employee(400, "Steve", 150),
                new Employee(190, "David", 400)
                ).collect(Collectors.toList());
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
        SparseMatrix<String> matrix = employeeService.traverseOrgchart(topManager);
        assertEquals(2, matrix.getMaxCol());
        assertEquals(5, matrix.getMaxRow());
        String expectMaxtrixString = "maxRow = 5 , maxCol = 2\n" +
                "0: row: 0 col: 0 value: Jamie\n" +
                "1: row: 1 col: 1 value: Alan\n" +
                "2: row: 2 col: 2 value: Martin\n" +
                "3: row: 3 col: 2 value: Alex\n" +
                "4: row: 4 col: 1 value: Steve\n" +
                "5: row: 5 col: 2 value: David\n";
        assertEquals(expectMaxtrixString, matrix.toString());
    }

    @Test
    void traverseOrgchart_Add_New_Employee_At_Lowest_Level() throws Exception {
        Employee employee = new Employee(900, "Tango", 190);
        employeeList.add(employee);
        Employee topManager = employeeService.buildOrgchart(employeeList);
        SparseMatrix<String> matrix = employeeService.traverseOrgchart(topManager);
        String expectMaxtrixString = "maxRow = 6 , maxCol = 3\n" +
                "0: row: 0 col: 0 value: Jamie\n" +
                "1: row: 1 col: 1 value: Alan\n" +
                "2: row: 2 col: 2 value: Martin\n" +
                "3: row: 3 col: 2 value: Alex\n" +
                "4: row: 4 col: 1 value: Steve\n" +
                "5: row: 5 col: 2 value: David\n" +
                "6: row: 6 col: 3 value: Tango\n";
        assertEquals(expectMaxtrixString, matrix.toString());
    }

    @Test
    void traverseOrgchart_Add_Employee_With_Invalid_Manger() throws Exception {
        Employee employee = new Employee(900, "Tango", 700);
        employeeList.add(employee);
        Employee topManager = employeeService.buildOrgchart(employeeList);
        SparseMatrix<String> matrix = employeeService.traverseOrgchart(topManager);
        String expectMaxtrixString = "maxRow = 5 , maxCol = 2\n" +
                "0: row: 0 col: 0 value: Jamie\n" +
                "1: row: 1 col: 1 value: Alan\n" +
                "2: row: 2 col: 2 value: Martin\n" +
                "3: row: 3 col: 2 value: Alex\n" +
                "4: row: 4 col: 1 value: Steve\n" +
                "5: row: 5 col: 2 value: David\n";
        assertEquals(expectMaxtrixString, matrix.toString());
    }

    @Test
    void traverseOrgchart_Add_Employee_With_No_Manager() throws Exception {
        Employee employee = new Employee(900, "Tango", null);
        employeeList.add(employee);
        Employee topManager = employeeService.buildOrgchart(employeeList);
        SparseMatrix<String> matrix = employeeService.traverseOrgchart(topManager);
        String expectMaxtrixString = "maxRow = 5 , maxCol = 2\n" +
                "0: row: 0 col: 0 value: Jamie\n" +
                "1: row: 1 col: 1 value: Alan\n" +
                "2: row: 2 col: 2 value: Martin\n" +
                "3: row: 3 col: 2 value: Alex\n" +
                "4: row: 4 col: 1 value: Steve\n" +
                "5: row: 5 col: 2 value: David\n";
        assertEquals(expectMaxtrixString, matrix.toString());
    }

    @Test
    void traverseOrgchart_removeEmployeeFromTheSecondLevel() throws Exception {
        employeeList.removeIf(employee->employee.getEmployeeID().intValue()==100);
        Employee topManager = employeeService.buildOrgchart(employeeList);
        SparseMatrix<String> matrix = employeeService.traverseOrgchart(topManager);
        String expectMaxtrixString = "maxRow = 2 , maxCol = 2\n" +
                "0: row: 0 col: 0 value: Jamie\n" +
                "1: row: 1 col: 1 value: Steve\n" +
                "2: row: 2 col: 2 value: David\n";
        assertEquals(expectMaxtrixString, matrix.toString());
    }

    @Test
    void traverseOrgchart_EmptyList() throws Exception {
        employeeList.clear();
        Employee topManager = employeeService.buildOrgchart(employeeList);
        SparseMatrix<String> matrix = employeeService.traverseOrgchart(topManager);
        String expectMaxtrixString = "maxRow = 0 , maxCol = 0\n";
        assertEquals(expectMaxtrixString, matrix.toString());
    }
}
