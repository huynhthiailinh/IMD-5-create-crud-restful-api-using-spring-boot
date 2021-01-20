package com.dut.employee.controller;

import com.dut.employee.handleJsonFilter.EmployeeFilter;
import com.dut.employee.model.Employee;
import com.dut.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeFilter employeeFilter;

    public EmployeeController(EmployeeService employeeService, EmployeeFilter employeeFilter) {
        this.employeeService = employeeService;
        this.employeeFilter = employeeFilter;
    }

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeFilter.getEmployee(employeeService.getEmployeeById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getEmployees() {
        return new ResponseEntity<>(employeeFilter.getEmployees(employeeService.getEmployees()), HttpStatus.OK);
    }

    @GetMapping("/age")
    public ResponseEntity getEmployeesByAge(@RequestParam("age") Long age) {
        return new ResponseEntity<>(employeeFilter.getEmployees(employeeService.getEmployeesByAge(age)), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity getEmployeesByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(employeeFilter.getEmployees(employeeService.getEmployeesByName(name)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity("Deleted", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }
}