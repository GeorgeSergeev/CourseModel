package ru.latypov.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.latypov.exception.EmployeeNotFound;
import ru.latypov.model.Employee;
import ru.latypov.service.EmployeeService;

import java.util.List;


/**
 * Контролер для  api/employee.
 */

@RestController
@RequestMapping("api/user")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * Слушаем /list.
     */

    @PostMapping(value = "/list")
    @ResponseBody
    public List<Employee> getEmployee() {


        return employeeService.retrieveEmployees();
    }

    /**
     * Слушаем /{id}.
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    public Employee getEmpoloyee(@PathVariable(name = "id") Integer id) throws EmployeeNotFound {
  
        return employeeService.getEmployee(id);
    }

    /**
     * Слушаем /update.
     */
    @PostMapping(value = "/update")
    public ResponseEntity updateEmployee(@RequestBody Employee employee) {
        Employee emp = employeeService.getEmployee(employee);
        if (emp != null) {
            employeeService.updateEmployee(employee);

        }

        return new ResponseEntity("success", HttpStatus.OK);
    }


    /**
     * Слушаем /save.
     */
    @PostMapping(value = "/save")
    public void saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);

    }

}



