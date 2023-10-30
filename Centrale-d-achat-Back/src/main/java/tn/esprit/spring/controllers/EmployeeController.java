package tn.esprit.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DTO.EmployeeDTO;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.interfaces.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }
    @GetMapping("/ed/{id}")
    public List<EmployeeDTO> getEmployeesByEmpDep(@PathVariable("id") Integer id) {
        return employeeService.getEmployeeByEmplacementDepartement(id);
    }

    @GetMapping("/details/{userId}")
    public String getEmployeeDetails(@PathVariable("userId") String userID){
        return employeeService.getEmployeeFonctionAndEmpDept(userID);
    }

    @PostMapping("/assign-employee/{empdepId}/{userId}")
    public Employee addEmployee(@RequestBody Employee employee,
                                @PathVariable("empdepId") Integer empdepId,
                                @PathVariable("userId") String userID) {
        return employeeService.addEmployee(employee,empdepId,userID);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
