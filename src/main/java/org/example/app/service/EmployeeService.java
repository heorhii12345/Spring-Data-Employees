package org.example.app.service;

import org.example.app.entity.domain.employee.Employee;
import org.example.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public Optional<Employee> save(Employee employee) {
        return Optional.of(repository.save(employee));
    }

    public Optional<List<Employee>> getAll() {
        return Optional.of(repository.findAll());
    }

    public Employee getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Employee update(Long id, Employee employee) {
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        String jobPosition = employee.getJobPosition();
        String phone = employee.getPhone();
        Optional<Employee> optional = repository.findById(id);
        if (optional.isPresent()) {
            Employee employeeUpdate = optional.get();
            if (firstName != null)
                employeeUpdate.setFirstName(firstName);
            if (lastName != null)
                employeeUpdate.setLastName(lastName);
            if (jobPosition != null)
                employeeUpdate.setJobPosition(jobPosition);
            if (phone != null)
                employeeUpdate.setPhone(phone);
            repository.save(employeeUpdate);
        }
        return repository.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else return false;
    }

    public List<Employee> getByFirstName(String firstName) {
        return repository.findByFirstName(firstName)
                .orElse(Collections.emptyList());
    }

    public List<Employee> getByLastName(String lastName) {
        return repository.findByLastName(lastName)
                .orElse(Collections.emptyList());
    }

    public List<Employee> getByJobPosition(String jobPosition) {
        return repository.findByJobPosition(jobPosition)
                .orElse(Collections.emptyList());
    }

    public List<Employee> getByPhone(String Phone) {
        return repository.findByPhone(Phone)
                .orElse(Collections.emptyList());
    }
}
