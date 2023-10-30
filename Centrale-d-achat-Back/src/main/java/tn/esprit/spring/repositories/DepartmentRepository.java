package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Department;

// Annotation
@Repository

// Interface
public interface DepartmentRepository
        extends JpaRepository<Department, Long> {
}
