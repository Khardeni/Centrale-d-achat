package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.AppelOffre;

@Repository
public interface AppelOffreRepository extends JpaRepository<AppelOffre, Integer> {
}