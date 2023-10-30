package tn.esprit.spring.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Conge;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.HolidaysCalender;
import tn.esprit.spring.interfaces.ICongeService;
import tn.esprit.spring.repositories.CongeRepository;
import tn.esprit.spring.repositories.EmployeeRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
    public class CongeImpl implements ICongeService {
    @Autowired
        CongeRepository congeRepository;
    @Autowired
    EmployeeRepository employeRepository;
        List<HolidaysCalender> holidaysList = new ArrayList<HolidaysCalender>() {{

            add(new HolidaysCalender(LocalDate.of(2023,1,1),"Nouvel An"));
            add(new HolidaysCalender(LocalDate.of(2023,1,14), "Jour anniversaire de la Révolution tunisienne"));
            add(new HolidaysCalender(LocalDate.of(2023,3,20), "Fête de l’Indépendance de la Tunisie"));
            add(new HolidaysCalender(LocalDate.of(2023,4,9), " Jour des Martyrs"));
            add(new HolidaysCalender(LocalDate.of(2023,5,1), " Fête du Travail"));
            add(new HolidaysCalender(LocalDate.of(2023,5,24), "Congés Aïd El Fitr"));
            add(new HolidaysCalender(LocalDate.of(2023,5,25), "Congés Aïd El Fitr"));
            add(new HolidaysCalender(LocalDate.of(2023,7,25), "Fête de la République"));
            add(new HolidaysCalender(LocalDate.of(2023,7,31), "Aïd El Idha "));
            add(new HolidaysCalender(LocalDate.of(2023,8,13), "Fête de la femme"));
            add(new HolidaysCalender(LocalDate.of(2023,8,1), "Aïd El Idha "));
            add(new HolidaysCalender(LocalDate.of(2023,9,20), "Jour de l’An Hégire 1441 (Ras El Am El Hijri)"));
            add(new HolidaysCalender(LocalDate.of(2023,10,15), "Fête de l’évacuation"));
            add(new HolidaysCalender(LocalDate.of(2023,10,29), "Anniversaire du prophète Mohamed (Mouled-Mawlid)"));
        }};

        @Override
        public Conge addConge(Conge conge) {
            return congeRepository.save(conge);
        }

        @Override
        public void deleteConge(Integer conge_id) {
            congeRepository.deleteById(conge_id);

        }

        @Override
        public void updateConge(Conge conge) {
            congeRepository.save(conge);

        }

        @Override
        public List<Conge> allConges() {
            return congeRepository.findAll();
        }

        @Override
        public Conge findConge(Integer conge_id) {
            return congeRepository.findById(conge_id).orElseThrow(() -> new RuntimeException("Congé not exist"));
        }

        @Override
        public void acceptConge(Conge conge) {
            int nbr = 0;
            long days = ChronoUnit.DAYS.between(conge.getDateDebut(), conge.getDateFin());
            if (conge.getSoldeConge() >= days) {

                for (HolidaysCalender day : holidaysList) {

                    if ((day.getDate().isAfter(conge.getDateDebut())) && (day.getDate().isBefore(conge.getDateFin()))){
                        nbr +=1;
                    }
                }
                conge.setConfirmation(true);
                conge.setSoldeConge((int) (conge.getSoldeConge()-days+nbr));
                congeRepository.save(conge);
            }
        }

        @Override
        public void refuseConge(Integer conge_id, String message) {
            Conge c = congeRepository.findById(conge_id).orElseThrow(() -> new RuntimeException("Congé not exist"));

            c.setConfirmation(false);
            c.setRaison(message);
            congeRepository.save(c);
        }

        @Override
        public List<Conge> allCongeById(Integer emp_id) {
            Employee u = employeRepository.findById(Long.valueOf(emp_id)).orElseThrow(() -> new RuntimeException("Absence not exist"));
            return u.getConges();
        }
}
