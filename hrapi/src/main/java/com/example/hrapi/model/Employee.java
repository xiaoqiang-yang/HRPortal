package com.example.hrapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "employeeid", nullable = false)
    private Integer employeeID;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "managerid")
    private Integer managerID;

    @Transient
    @JsonIgnore
    private List<Employee> subordinates;
    public void addSubordinate(Employee employee){
        if(subordinates == null){
            subordinates = new ArrayList<>();
        }
        subordinates.add(employee);
    }
}
