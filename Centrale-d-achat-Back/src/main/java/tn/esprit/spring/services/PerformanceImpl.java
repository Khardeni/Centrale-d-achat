package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Performance;
import tn.esprit.spring.interfaces.PerformanceService;
import tn.esprit.spring.repositories.EmplacementRepository;
import tn.esprit.spring.repositories.EmployeeRepository;
import tn.esprit.spring.repositories.PerformanceRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@EnableScheduling
@Slf4j
public class PerformanceImpl implements PerformanceService {
    @Autowired
    private EmplacementRepository emplacementRepository;


    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PerformanceRepository performanceRepository;

    @Override
    public void addPerformanceToEmployee(Integer employeeId, Double performanceRating) {
        // get the employee by id
        Employee employee = employeeRepository.findById(Long.valueOf(employeeId)).orElse(null);
        if (employee == null) {
            // handle case where employee is not found
            throw new NotFoundException("Employee not found with id " + employeeId);
        }
        // create a new performance entity
        Performance performance = new Performance();
        performance.setPerformanceRating(performanceRating);
        performance.setPerformanceDate(LocalDate.now());
        performance.setEmployeP(employee);


        // save the performance entity and update the employee
        performanceRepository.save(performance);

        // calculate the new salary based on the performance rating
        Double salary = employee.getSalary();
        if (performanceRating >= 9.0) {
            salary = salary * 1.2;
        } else if (performanceRating >= 7.0) {
            salary = salary * 1.1;
        }

        // update the employee's salary
        employee.setSalary(salary);
        employeeRepository.save(employee);
        if (employee == null) {
            // Gérer le cas où l'employé n'est pas trouvé
            return;
        }

    }

    @Scheduled(cron=" 0 0 0 1/1 * ?")
    public void updateWorkedHours(){
        List<Employee> le = employeeRepository.findAll();
        for(Employee e : le){
            e.setTotalWorkedHours(e.getTotalWorkedHours()+8);
            employeeRepository.save(e);
        }
    }

    @Override
    public void updateSalaryForExcellentPerformance(Integer employeeId) {
        Employee employee = employeeRepository.findById(Long.valueOf(employeeId)).orElse(null);
        if (employee == null) {
            throw new NotFoundException("Employee not found with id " + employeeId);
        }

        // Check if the employee has any performance records
        List<Performance> performances = performanceRepository.findByEmployeP(employee);
        if (performances == null || performances.isEmpty()) {
            return;
        }

        // Calculate the average performance rating for the employee
        double totalRating = 0.0;
        int count = 0;
        for (Performance p : performances) {
            if (p.getPerformanceRating() != null) {
                totalRating += p.getPerformanceRating();
                count++;
            }
        }
        double averageRating = totalRating / count;

        // If the average rating is excellent, update the salary
        if (averageRating >= 3.5) {
            double currentSalary = employee.getSalary();
            double newSalary = currentSalary * 1.1; // Increase salary by 10%
            employee.setSalary(newSalary);
            employeeRepository.save(employee);
        }
    }

  @Override
    public List<Performance> getAllPerformancesForEmployee(Integer employeeId) {
        Employee employee = employeeRepository.findById(Long.valueOf(employeeId)).orElse(null);
        if (employee == null) {
            throw new NotFoundException("Employee not found with id " + employeeId);
        }
        return performanceRepository.findByEmployeP(employee);
    }




}
