package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.DemandeAchat;

public interface DemandeAchatRepository extends JpaRepository<DemandeAchat, Integer> {
}