package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Department;
import tn.esprit.spring.entities.EmplacementDepartement;
import tn.esprit.spring.interfaces.EmpDeptService;

import java.util.List;

@RestController
@RequestMapping("/empd/")
public class EmplacementDepartementController {

    @Autowired
    private EmpDeptService empDeptService;

    @GetMapping
    public List<EmplacementDepartement> getAllEmplacementDepartements() {
        return empDeptService.getAll();
    }

    @GetMapping("/add/{emplacementId}/{departementId}")
    public void addArticleEmplacement(@PathVariable("emplacementId") Integer emplacementId,
                                      @PathVariable("departementId") Long departementId) {
            empDeptService.addEmpDept(emplacementId,departementId);
    }

    @GetMapping("/{idEmp}")
    public List<Department> getListDeptsByEmp(@PathVariable("idEmp") Integer idEmp){
        return empDeptService.getAllDeptsByEmplacement(idEmp);
    }

}