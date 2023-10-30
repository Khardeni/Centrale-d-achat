package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Department;
import tn.esprit.spring.entities.Emplacement;
import tn.esprit.spring.entities.EmplacementDepartement;
import tn.esprit.spring.interfaces.EmpDeptService;
import tn.esprit.spring.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpDeptImpl implements EmpDeptService {

    @Autowired
    private EmplacementDepartmentRepository emplacementDepartmentRepository;
    @Autowired
    private EmplacementRepository emplacementRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void addEmpDept(Integer emplacementId, Long departementId){
        EmplacementDepartement empdep = new EmplacementDepartement();
        Emplacement emplacement = emplacementRepository.findById(emplacementId).orElse(null);
        Department department = departmentRepository.findById(departementId).orElse(null);
        empdep.setEmplacementId(emplacement);
        empdep.setDepartementId(department);
        emplacementDepartmentRepository.save(empdep);
    }

    @Override
    public void deleteEmpDept(Integer empdepId) {
        emplacementDepartmentRepository.deleteById(empdepId);
    }

    @Override
    public List<EmplacementDepartement> getAll() {
        return emplacementDepartmentRepository.findAll();
    }
    @Override
    public List<Department> getAllDeptsByEmplacement(Integer emplacementId) {
        Emplacement emplacement = emplacementRepository.findById(emplacementId).orElse(null);
        List<Department> ld = new ArrayList<>();
        List<EmplacementDepartement> led = emplacementDepartmentRepository.findByEmplacementId(emplacement);
        for(EmplacementDepartement ed : led){
            ld.add(ed.getDepartementId());
        }
        return ld;
    }

}
