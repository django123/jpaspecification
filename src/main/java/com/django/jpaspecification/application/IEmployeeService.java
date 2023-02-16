package com.django.jpaspecification.application;

import com.django.jpaspecification.domain.Employee;

import java.util.List;
import java.util.UUID;

public interface IEmployeeService {

    Employee save(Employee employee);
    List<Employee>findAll();

    Employee findById(UUID id);
    void delete(UUID id);
    List<Employee> findEmployeeByName(String name);
    List<Employee>findEmployeeByPhoneType();
    List<Employee> getByOptCriteria(String fn, String ln);
}
