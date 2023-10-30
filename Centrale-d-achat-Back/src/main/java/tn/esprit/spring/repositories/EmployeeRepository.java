package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.EmplacementDepartement;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.User;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee findByUser(User user);
    public List<Employee> findByEmplacementDepartement(EmplacementDepartement emplacementDepartement);
}
