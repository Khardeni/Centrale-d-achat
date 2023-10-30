package tn.esprit.spring.interfaces;

import tn.esprit.spring.DTO.AbsEmpDTO;
import tn.esprit.spring.entities.Absence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IAbsenceService {
    // @Override
    //public Absence addAbsence(Absence absence) {
    //  return absenceRepository.save(absence);
//    }
  //  Absence save(Absence absence);

    //Absence addAbsence(Absence absence);

   // Absence ajoutAbsence(Absence absence);

    List<Absence> findAllAbsences();

    Optional<Absence> findAbsenceById(Integer id);

    Absence updateAbsence(Integer absenceId, Absence updatedAbsence);

    void deleteAbsence(Integer id);

   // Absence addAbsence(Integer employeId, Absence absence);

    /* @Override
     public Absence addAbsence(Integer employeId , Absence absence) {
         Employes employe = employeRepository.findById(employeId).orElse(null);
         // Check if the absence dates overlap with any existing absences for the same employee
         List<Absence> conflictingAbsences = absenceRepository.findConflictingAbsences(
                 absence.getEmployeA().getEmployeId(), absence.getStartDate(), absence.getEndDate());
         if (!conflictingAbsences.isEmpty()) {
             throw new NotFoundException("Absence dates conflict with an existing absence");
         }
         // Save the absence to the database
         return absenceRepository.save(absence);
     }*/
   /*  @Override

     public Absence approveAbsence(Integer absenceId) {
         Absence absence = absenceRepository.findById(absenceId)
                 .orElseThrow(() -> new NotFoundException("Absence not found"));
         if (absence.getStatus() != Status.PENDING) {
             throw new NotFoundException("Absence is not pending approval");
         }
         absence.setStatus(Status.APPROVED);
         return absenceRepository.save(absence);
     }
     @Override

     public Absence rejectAbsence(Integer absenceId) {
         Absence absence = absenceRepository.findById(absenceId)
                 .orElseThrow(() -> new NotFoundException("Absence not found"));
         if (absence.getStatus() != Status.PENDING) {
             throw new NotFoundException("Absence is not pending approval");
         }
         absence.setStatus(Status.REJECTED);
         return absenceRepository.save(absence);
     }*/


    Absence addAbsence(Absence absence, Integer articleId);

    Absence approveAbsence(Integer absenceId);

    Absence rejectAbsence(Integer absenceId);

   // void updateEmployeeWorkHours(Absence absence, boolean subtractHours);

  //  double calculateWorkedHoursPercentage(Employes employee);

   // double calculateWorkedHoursPercentage(Integer employeId);

    double calculateWorkedHoursPercentage(Integer employeId, Integer absenceId);

    List<AbsEmpDTO> GetTime(LocalDate startDate);

    AbsEmpDTO addAbsemp(AbsEmpDTO abemp);


    AbsEmpDTO updateAbsemp(Integer absenceId, AbsEmpDTO absEmpDTO);

    List<AbsEmpDTO> absences(Long employeeId);


    //void assignAbsenceToEmployee(Integer employeId, Integer AbsId, Date startDate, Date endDate);
    //Absence addAbsence(Absence absence);


}
