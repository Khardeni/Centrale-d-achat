package tn.esprit.spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Absence;
import tn.esprit.spring.entities.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Integer> {

    List<Absence> findByEmployeA(Employee employee);
    List<Absence> findByStartDate(LocalDate sDate);
   // @Query("SELECT a FROM Absence a WHERE a.employeA.employeId = :employeId AND a.startDate BETWEEN :startDate AND :endDate")
    //List<Absence> findAbsencesByEmployeIdAndDateRange(@Param("employeId") Integer employeId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

   // @Query("SELECT a FROM Absence a WHERE a.employeA.employeId = :employeId AND a.endDate <= :startDate AND a.startDate >= :endDate")
    //List<Absence> findConflictingAbsences(@Param("employeId") Integer employeId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    //List<Absence> findAbsencesByEmployeAEmployeId(Integer employeeId);

}