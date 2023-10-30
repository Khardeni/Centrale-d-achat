package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.Conge;

import java.util.List;

public interface ICongeService {
    Conge addConge(Conge conge);

    void deleteConge(Integer conge_id);

    void updateConge(Conge conge);

    List<Conge> allConges();

    Conge findConge(Integer conge_id);

    void acceptConge(Conge conge);

    void refuseConge(Integer conge_id, String message);

    List<Conge> allCongeById(Integer emp_id);
}
