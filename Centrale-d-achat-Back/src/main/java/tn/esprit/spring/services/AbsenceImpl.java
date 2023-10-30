package tn.esprit.spring.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DTO.AbsEmpDTO;
import tn.esprit.spring.entities.Absence;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Status;
import tn.esprit.spring.interfaces.IAbsenceService;
import tn.esprit.spring.repositories.AbsenceRepository;
import tn.esprit.spring.repositories.EmployeeRepository;
import tn.esprit.spring.repositories.WorkedHoursRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j

@RequiredArgsConstructor
public class AbsenceImpl implements IAbsenceService {

    @Autowired
    AbsenceRepository absenceRepository;
    @Autowired
    EmployeeRepository employeRepository;
    @Autowired
    WorkedHoursRepository workedHoursRepository;


    @Override
    public List<Absence> findAllAbsences() {
        return absenceRepository.findAll();
    }

    @Override
    public Optional<Absence> findAbsenceById(Integer id) {
        return absenceRepository.findById(id);
    }

    @Override
    public Absence updateAbsence(Integer absenceId, Absence updatedAbsence) {
        Absence abs = absenceRepository.findById(absenceId).orElse(null);
        if (abs == null) {
            throw new NotFoundException("Charge with id " + absenceId + " not found");
        }
        abs.setStartDate(updatedAbsence.getStartDate());
        abs.setEndDate(updatedAbsence.getStartDate());
        abs.setIsAbsent(false);
        return absenceRepository.save(abs);
    }

    @Override
    public void deleteAbsence(Integer id) {
        absenceRepository.deleteById(id);
    }

    @Override
    public Absence addAbsence(Absence absence, Integer employeId) {
        Employee employes = employeRepository.findById(Long.valueOf(employeId)).orElse(null);
        absence.setEmployeA(employes);
        absence.setStatus(Status.PENDING);
        return absenceRepository.save(absence);
    }

    public Absence approveAbsence(Integer absenceId) {
        Absence absence = absenceRepository.findById(absenceId).orElse(null);
        absence.setStatus(Status.APPROVED);
        LocalDate startDate = absence.getStartDate();
        LocalDate endDate = absence.getEndDate();
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        log.info(String.valueOf(absence.getEmployeA()));

        Employee employee = absence.getEmployeA();
        int hoursInDay = 8;
        int totalHours = hoursInDay * (int) daysBetween;
        employee.setTotalWorkedHours(employee.getTotalWorkedHours() - totalHours);
        employeRepository.save(employee);
        return absenceRepository.save(absence);
    }

    public Absence rejectAbsence(Integer absenceId) {
        Absence absence = absenceRepository.findById(absenceId).orElse(null);
        absence.setStatus(Status.REJECTED);
        return absenceRepository.save(absence);
    }

    @Override
    public double calculateWorkedHoursPercentage(Integer employeId,Integer absenceId) {
        Employee emp = employeRepository.findById(Long.valueOf(employeId)).orElse(null);
        Absence absence = absenceRepository.findById(absenceId).orElse(null);
        log.info(String.valueOf(employeId));
        double totalWorkHours =  ChronoUnit.DAYS.between(absence.getStartDate(), absence.getEndDate()) + 1;
        log.info(String.valueOf(emp.getTotalWorkedHours()));
        double workedHours =emp.getTotalWorkedHours()- totalWorkHours ;
        log.info(String.valueOf(emp.getTotalWorkedHours()));
        double percentage = (workedHours / totalWorkHours) * 0.1;
        log.info(String.valueOf(percentage));
        return percentage;
    }

    @Override
    public List<AbsEmpDTO> GetTime(LocalDate startDate) {
        List<AbsEmpDTO> AbsL = new ArrayList<>();
        List<Absence> listes = absenceRepository.findByStartDate(startDate);
        for (Absence ab : listes) {
            AbsEmpDTO amp = new AbsEmpDTO();
            amp.setEmployeeId(ab.getEmployeA().getEmployeeId());
            amp.setAbsenceId(ab.getAbsenceId());
            amp.setStartDate(ab.getStartDate().atStartOfDay().toLocalDate());
            amp.setEndDate(ab.getEndDate().atStartOfDay().toLocalDate());
            amp.setReason(ab.getReason());
            amp.setAbsenceType(ab.getAbsenceType());
            AbsL.add(amp);
        }
        return AbsL;
    }
    @Override
    public AbsEmpDTO addAbsemp(AbsEmpDTO absEmpDTO) {
        Employee employee = new Employee();
        employee = employeRepository.save(employee);

        Absence absence = new Absence();
        absence.setEmployeA(employee);
        absence.setStartDate(absEmpDTO.getStartDate());
        absence.setEndDate(absEmpDTO.getEndDate());
        absence.setReason(absEmpDTO.getReason());
        absence.setAbsenceType(absEmpDTO.getAbsenceType());
        absence = absenceRepository.save(absence);

        absEmpDTO.setEmployeeId(employee.getEmployeeId());
        absEmpDTO.setAbsenceId(absence.getAbsenceId());
        absEmpDTO.setReason(absence.getReason());
        absEmpDTO.setReason(absence.getReason());
        absEmpDTO.setStartDate(absence.getStartDate());
        absEmpDTO.setEndDate(absence.getEndDate());
        absEmpDTO.setAbsenceType(absence.getAbsenceType());

        return absEmpDTO;
    }

    @Override
    public AbsEmpDTO updateAbsemp(Integer absenceId, AbsEmpDTO absEmpDTO) {
        Absence absence = absenceRepository.findById(absenceId).orElse(null);
        Employee employee = absence.getEmployeA();

        absence.setStartDate(absEmpDTO.getStartDate());
        absence.setEndDate(absEmpDTO.getEndDate());
        absence.setReason(absEmpDTO.getReason());
        absence.setAbsenceType(absEmpDTO.getAbsenceType());

        employeRepository.save(employee);
        absenceRepository.save(absence);

        absEmpDTO.setEmployeeId(employee.getEmployeeId());
        absEmpDTO.setAbsenceId(absence.getAbsenceId());
        absEmpDTO.setReason(absence.getReason());
        absEmpDTO.setStartDate(absence.getStartDate());
        absEmpDTO.setEndDate(absence.getEndDate());
        absEmpDTO.setAbsenceType(absence.getAbsenceType());

        return absEmpDTO;
    }

    @Override
    public List<AbsEmpDTO> absences(Long employeeId){   /// not in USE
        List<AbsEmpDTO> absemp =  new ArrayList<>();
        AbsEmpDTO absEmpDTO = new AbsEmpDTO();
        Employee employee = employeRepository.findById(employeeId).orElse(null);
        log.info(String.valueOf(employeeId));
        List<Absence> absences = absenceRepository.findByEmployeA(employee);
        return  absemp;


    }

}






