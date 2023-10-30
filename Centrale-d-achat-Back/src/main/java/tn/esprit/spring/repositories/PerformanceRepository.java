package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Performance;

import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Integer> {
    public List<Performance> findByEmployeP(Employee employee);
}
