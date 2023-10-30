package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DTO.AbsEmpDTO;
import tn.esprit.spring.entities.Absence;
import tn.esprit.spring.interfaces.IAbsenceService;
import tn.esprit.spring.interfaces.EmployeeService;
import tn.esprit.spring.repositories.AbsenceRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/Absence")
public class AbsenceRestController {
@Autowired
    IAbsenceService iAbsenceService;
@Autowired
EmployeeService employeRepository;
@Autowired
    AbsenceRepository absenceRepository;

    ///POST

    @PostMapping("/add-Absence/{employeId}")                      ///tested
    @ResponseBody
    public Absence addAbsence(@RequestBody Absence absence,@PathVariable("employeId")Integer employeId) {
        return iAbsenceService.addAbsence(absence,employeId);
   }
   @GetMapping("/Get-Al-Absences/")
   public List<AbsEmpDTO> GetTime(@RequestParam("StartDate")String startDate,@RequestParam("EndDate")String endDate){
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
       LocalDate parsedDate = LocalDate.parse(startDate, formatter);
       return iAbsenceService.GetTime(parsedDate);
   }
    @GetMapping("/ListAbsEmp/{employeeId}")
    public List<AbsEmpDTO> absences (@PathVariable("employeeId") Long employeeId){
        return iAbsenceService.absences(employeeId);
    }

    @PostMapping("/addAbsemp")
        @ResponseBody
        public AbsEmpDTO add (@RequestBody AbsEmpDTO absEmpDTO){
        return iAbsenceService.addAbsemp(absEmpDTO);

    }
    @PutMapping("updateAbsEmp/{absenceId}")
        public AbsEmpDTO updateAbsemp(@PathVariable("absenceId") Integer absenceId,@RequestBody AbsEmpDTO absEmpDTO) {
        return iAbsenceService.updateAbsemp(absenceId,absEmpDTO)       ;

        }

        @GetMapping("/Get-All-Absences")          ///tested
    public List<Absence> getAllAbsences() {
        return iAbsenceService.findAllAbsences();
    }

    @GetMapping("/Get-Absence-ById/{id}")       ///tested
    public ResponseEntity<Absence> getAbsenceById(@PathVariable Integer id) {
        Optional<Absence> absence = iAbsenceService.findAbsenceById(id);
        if (absence.isPresent()) {
            return ResponseEntity.ok(absence.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/Update-Absence/{id}")       ///tested
    public ResponseEntity<Absence> updateAbsence(@PathVariable Integer id, @RequestBody Absence updatedAbsence) {
        Optional<Absence> existingabs = iAbsenceService.findAbsenceById(id);
        if (existingabs.isPresent()) {
            updatedAbsence.setAbsenceId(id);
            return ResponseEntity.ok(iAbsenceService.updateAbsence(id, updatedAbsence));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-Absence/{id}")                ///tested
    public ResponseEntity<Void> deleteAbsence(@PathVariable Integer id) {
        Optional<Absence> existabs = iAbsenceService.findAbsenceById(id);
        if (existabs.isPresent()) {
            iAbsenceService.deleteAbsence(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

        @PostMapping("/approuve-absence/{absenceId}")
        public Absence approveAbsence(@PathVariable("absenceId") Integer absenceId) {
            return iAbsenceService.approveAbsence(absenceId);
        }
    @PostMapping("/reject-absence/{absenceId}")
    public Absence rejectAbsence(@PathVariable("absenceId") Integer absenceId) {
        return iAbsenceService.rejectAbsence(absenceId);
    }


       /* @GetMapping("/Confliced-employees/{employeeId}")
    public List<Absence> findConflictingAbsences(@PathVariable Integer employeeId,
                                                 @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                 @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Absence> absences = absenceRepository.findAbsencesByEmployeAEmployeId(employeeId);
        List<Absence> conflicts = new ArrayList<>();

        for (Absence absence : absences) {
            if ((absence.getStartDate().isBefore(endDate) && absence.getEndDate().isAfter(startDate)) ||
                    (absence.getStartDate().isEqual(startDate) || absence.getEndDate().isEqual(endDate))) {
                conflicts.add(absence);
            }
        }

        return conflicts;
    }*/
    @GetMapping("/calculateWorkedHours/{employeId}/{AbsenceId}")
    public double calculateWorkedHoursPercentage(@PathVariable("employeId") Integer employeId,@PathVariable("AbsenceId")Integer absenceId) {
       return iAbsenceService.calculateWorkedHoursPercentage(employeId,absenceId);
    }




    }