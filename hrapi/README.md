# HR API

This project was generated with Springboot initializr.

## Development server

Run `mvn spring-boot:run` for a dev server. Navigate to `http://localhost:8080/api/v1/employees` to check the first API result

## Build

Run `mvn package` to build the project.

## Running unit tests

Run `mvn test` to execute the unit tests .

## Build Docker image
First run `mvn:package` and then run `docker build . -t hrapi:latest` to build the docker image with tag hrapi:latest

## API endpoints
there are four API endpoints provided 

| URL                       | Method  | Comments    | 
|---------------------------|-------- |---------|
|/api/v1/employees          | GET    | Get ALL Employees Information |     |
|/api/v1/employees          | POST   | Create a new employee | |
|/api/v1/employees/{id}     | DELET  | Delete a employee |                  |
|/api/v1/orgchart           | GET  | Return orgnization reportTo table with a sparse matrix |   
