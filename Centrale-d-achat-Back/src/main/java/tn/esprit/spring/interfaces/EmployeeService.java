package tn.esprit.spring.interfaces;

import tn.esprit.spring.DTO.EmployeeDTO;
import tn.esprit.spring.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(Long id);
    public String getEmployeeFonctionAndEmpDept(String userID);
    public Employee addEmployee(Employee employee, Integer empdepId, String userID);
    public Employee updateEmployee(Long id, Employee employee);
    public void deleteEmployee(Long id);
    public List<EmployeeDTO> getEmployeeByEmplacementDepartement(Integer id);
}
