package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.Department;
import tn.esprit.spring.entities.EmplacementDepartement;

import java.util.List;

public interface EmpDeptService {
    void addEmpDept(Integer emplacementId, Long departementId);
    void deleteEmpDept(Integer empdepId);
    List<EmplacementDepartement> getAll();

    List<Department> getAllDeptsByEmplacement(Integer emplacementId);
}
