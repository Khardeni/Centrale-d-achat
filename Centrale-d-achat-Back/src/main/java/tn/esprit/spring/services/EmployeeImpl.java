package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DTO.EmployeeDTO;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.EmployeeService;
import tn.esprit.spring.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmplacementDepartmentRepository emplacementDepartmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmplacementRepository emplacementRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public String getEmployeeFonctionAndEmpDept(String userID) {
        User user = userRepository.findById(userID).orElse(null);
        Employee employee = employeeRepository.findByUser(user);
        EmplacementDepartement empdept = emplacementDepartmentRepository.findById(employee.getEmplacementDepartement().getEmplacementDepartementId()).orElse(null);
        Emplacement emplacement = emplacementRepository.findById(empdept.getEmplacementId().getEmplacementId()).orElse(null);
        Department department = departmentRepository.findById(Long.valueOf(empdept.getDepartementId().getDepartmentId())).orElse(null);
        return user.getUserLastName()+" "+user.getUserFirstName()+" "+employee.getJobTitle()+" "+employee.getSalary()+" "+emplacement.getNomEmplacement()+" "+department.getDepartmentName();
    }

    public Employee addEmployee(Employee employee, Integer empdepId, String userID) {
        EmplacementDepartement empdep = emplacementDepartmentRepository.findById(empdepId).orElse(null);
        User user = userRepository.findById(userID).orElse(null);
        employee.setTotalWorkedHours(Double.valueOf(0));
        employee.setEmplacementDepartement(empdep);
        employee.setUser(user);
        return employeeRepository.save(employee);
    }
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee == null) {
            return null;
        }
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setJobTitle(employee.getJobTitle());
        return employeeRepository.save(existingEmployee);
    }
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getEmployeeByEmplacementDepartement(Integer id) {
        EmplacementDepartement empdep = emplacementDepartmentRepository.findById(id).orElse(null);
        List<EmployeeDTO> ledto = new ArrayList<>();
        //EmployeeDTO eDTO = new EmployeeDTO();
        List<Employee> le = employeeRepository.findByEmplacementDepartement(empdep);
        for(Employee e : le){
                EmployeeDTO eDTO = new EmployeeDTO();
               eDTO.setEmployeeId(e.getEmployeeId());
               eDTO.setSalary(e.getSalary());
               eDTO.setJobTitle(e.getJobTitle());
               eDTO.setTotalWorkedHours(e.getTotalWorkedHours());
               User user = userRepository.findByUserName(e.getUser().getUserName());
               eDTO.setFullName(user.getUserFirstName()+" "+user.getUserLastName());
               ledto.add(eDTO);
        }
        return ledto;
    }
}
