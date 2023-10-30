package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.WorkedHours;

@Repository
public interface WorkedHoursRepository extends JpaRepository<WorkedHours,Integer> {

      //  @Query("SELECT COALESCE(SUM(w.workHours), 0) FROM WorkedHours w WHERE w.employeH.employeId = :employeId AND w.startDate BETWEEN :startDate AND :endDate")
       // Double getTotalWorkedHours(@Param("employeId") Integer employeId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    }


