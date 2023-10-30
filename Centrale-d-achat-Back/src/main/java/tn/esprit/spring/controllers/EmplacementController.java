package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DTO.EmplDTO;
import tn.esprit.spring.entities.Department;
import tn.esprit.spring.entities.Emplacement;
import tn.esprit.spring.entities.EmplacementDepartement;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.interfaces.EmpDeptService;
import tn.esprit.spring.interfaces.EmplacementService;
import tn.esprit.spring.repositories.EmplacementDepartmentRepository;
import tn.esprit.spring.repositories.EmplacementRepository;
import tn.esprit.spring.repositories.EmployeeRepository;
import tn.esprit.spring.services.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emplacements")
public class EmplacementController {

    @Autowired
    private EmplacementService emplacementService;

    @Autowired
    private EmpDeptService empDeptService;
    @Autowired
    private EmplacementRepository emplacementRepository;
    @Autowired
    private EmplacementDepartmentRepository emplacementDepartmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void initEmplacement() {
        emplacementService.initEmplacement();
    }



    @GetMapping("/getall")
    public List<Emplacement> getAllEmplacements() {
        return emplacementService.getAllEmplacements();
    }


    @GetMapping("/{id}")
    public Emplacement getEmplacementById(@PathVariable("id") Integer id){
        return emplacementRepository.findById(id).orElse(null);
    }

    @GetMapping("/detail/{id}")
    public List<EmplDTO> getEmplDetails(@PathVariable("id") Integer id){
        List<EmplDTO> finallist = new ArrayList<>();
        Emplacement emp = emplacementRepository.findById(id).orElse(null);
        List<Department> ld = empDeptService.getAllDeptsByEmplacement(emp.getEmplacementId());
        for(Department d:ld) {
            EmplDTO emplDTO = new EmplDTO();
            EmplacementDepartement empdep = emplacementDepartmentRepository.findByEmplacementIdAndDepartementId(emp,d);
            List<Employee> le = employeeRepository.findByEmplacementDepartement(empdep);
            emplDTO.setEmplacementId(emp.getEmplacementId());
            emplDTO.setDepartementId(d.getDepartmentName());
            emplDTO.setEmplacementDepartementId(empdep.getEmplacementDepartementId());
            emplDTO.setNbEmployee(le.size());
            finallist.add(emplDTO);
        }
        return finallist;
    }

    @PostMapping("/add/")
    public ResponseEntity<Void> addEmplacement(@RequestBody Emplacement emplacement) {
        emplacementService.addEmplacement(emplacement);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmplacement(@PathVariable int id, @RequestBody Emplacement emplacement) {
        emplacementService.updateEmplacement(id, emplacement);
        return new ResponseEntity<>(HttpStatus.OK);



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmplacement(@PathVariable int id) {
        emplacementService.deleteEmplacement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}