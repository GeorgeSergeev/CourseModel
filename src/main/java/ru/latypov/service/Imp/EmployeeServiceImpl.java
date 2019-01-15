package ru.latypov.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.latypov.exception.EmployeeNotFound;
import ru.latypov.model.Employee;
import ru.latypov.repository.EmployeeRepository;
import ru.latypov.service.EmployeeService;

import java.util.List;
import java.util.Optional;


@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Employee getEmployee(Integer id) throws EmployeeNotFound {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFound((id)));

    }

    public Employee getEmployee(Employee employee) {
        Optional<Employee> optEmp1 = employeeRepository.findById(employee.getId());
        return optEmp1.get();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}