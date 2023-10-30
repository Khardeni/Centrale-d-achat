package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.Performance;

import java.util.List;

public interface PerformanceService {
    void addPerformanceToEmployee(Integer employeeId, Double performanceRating);

    void updateSalaryForExcellentPerformance(Integer employeeId);

    List<Performance> getAllPerformancesForEmployee(Integer employeeId);
}