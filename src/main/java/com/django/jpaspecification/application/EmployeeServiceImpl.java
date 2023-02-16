package com.django.jpaspecification.application;


import com.django.jpaspecification.domain.Employee;
import com.django.jpaspecification.domain.PhoneType;
import com.django.jpaspecification.infrastructure.EmployeeRepository;
import com.django.jpaspecification.infrastructure.specifications.EmployeeSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(UUID id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(UUID id) {
       employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findEmployeeByName(String name) {
        return employeeRepository.findAll(EmployeeSpecs.getEmployeeByFirstNameSpec(name));
    }

    @Override
    public List<Employee> findEmployeeByPhoneType() {
        return employeeRepository.findAll(EmployeeSpecs.getEmployeesByPhoneTypeSpec(PhoneType.Cell));
    }

    @Override
    public List<Employee> getByOptCriteria(String fn, String ln) {
        Specification<Employee> spec = Specification
                .where(EmployeeSpecs.getEmployeeByFirstNameSpec(fn))
                .and("lastName" == null ? null : EmployeeSpecs.getEmployeeByLastName(ln));

        return employeeRepository.findAll(spec);
    }
}
