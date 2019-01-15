package ru.latypov.service;


import ru.latypov.exception.EmployeeNotFound;
import ru.latypov.model.Employee;

import java.util.List;
/*Набор методов для контролера Работник */
public interface EmployeeService {
    public List<Employee> retrieveEmployees();

    public Employee getEmployee(Integer id) throws EmployeeNotFound;

    public void saveEmployee(Employee employee);

    public Employee updateEmployee(Employee employee);

    public Employee getEmployee(Employee employee);
}
