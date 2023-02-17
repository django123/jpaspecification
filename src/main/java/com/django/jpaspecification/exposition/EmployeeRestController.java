package com.django.jpaspecification.exposition;

import com.django.jpaspecification.application.IEmployeeService;
import com.django.jpaspecification.domain.Employee;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/employee")
public class EmployeeRestController {

    private final IEmployeeService employeeService;

    public EmployeeRestController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable UUID id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Employee>saveEmployee(@RequestBody @Valid Employee employee){
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @GetMapping("{name}")
    public ResponseEntity<List<Employee>>findEmployeeByName(@PathVariable String name){
        List<Employee> employees = employeeService.findEmployeeByName(name);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/type")
    public ResponseEntity<List<Employee>>findEmployeeByPhoneType(){
        List<Employee> employees = employeeService.findEmployeeByPhoneType();
        return ResponseEntity.ok(employees);
    }

    public ResponseEntity<Void>delete(@PathVariable UUID id){
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
