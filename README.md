# HRPortal
Display the company hierachy information with a table

## Modules
this Repository include two sub modules:

1: HRClient for frontend build with Angular 9

2: HRAPI for backend microservice build with Spring boot

## Prerequisite
This project need   
    JDK 8+  
    Maven 3+  
    Angular 9  
the backend database is H2, no need any database setup work before run this application.  

## Run this application
Enter the hrapi directrory and execute:

`mvn spring-boot:run`

In other terminatal,enter the directory hrclient and execute:  
 
`ng serve`

## Verify the result
Visit `http//localhost:4200` , it will display a company's hierachy inforation in a table.

You can add/delete an employee to check the new hierachy information

## Where are the main functions
The function to get the sparse matrix reports information can get from   
`public Employee buildOrgchart(List<Employee> employees)`  
https://github.com/xiaoqiang-yang/HRPortal/blob/master/hrapi/src/main/java/com/example/hrapi/service/EmployeeService.java  

The function to draw the table in the front end ui can get from  
https://github.com/xiaoqiang-yang/HRPortal/blob/master/hrclient/src/app/hierachy-report/hierachy-report.component.ts  
and  
https://github.com/xiaoqiang-yang/HRPortal/blob/master/hrclient/src/app/hierachy-report/hierachy-report.component.html
