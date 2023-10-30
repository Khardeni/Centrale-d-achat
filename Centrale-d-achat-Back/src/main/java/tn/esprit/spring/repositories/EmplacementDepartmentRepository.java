package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Department;
import tn.esprit.spring.entities.Emplacement;
import tn.esprit.spring.entities.EmplacementDepartement;

import java.util.List;

@Repository
public interface EmplacementDepartmentRepository extends JpaRepository<EmplacementDepartement, Integer> {
    public List<EmplacementDepartement> findByEmplacementId(Emplacement emplacement);

    public EmplacementDepartement findByEmplacementIdAndDepartementId(Emplacement emplacement,Department department);
}