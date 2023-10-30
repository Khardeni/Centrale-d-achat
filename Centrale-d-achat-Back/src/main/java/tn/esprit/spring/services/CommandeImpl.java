package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Commande;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.CommandeRepository;
import tn.esprit.spring.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class CommandeImpl implements tn.esprit.spring.interfaces.ICommandeService {
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Commande AjouterCommande(Commande commande) {
      return  commandeRepository.save(commande);

    }
    @Override
    public Commande GetCommandeById(Integer cmdId) {
       return commandeRepository.getOne(cmdId);
    }
    @Override
    public List<Commande> GetListCommandes(){
        List<Commande> commandeList= commandeRepository.findAll();
        return commandeList;
    }
    @Override
    public List<Commande> GetCommandeByUser(String UID){
        User user= userRepository.findByUserName(UID);
        List<Commande> UserCommande= commandeRepository.findByUserId(user);
        return UserCommande;
    }
    @Override
    public List<Commande> GetCommandeByDate(LocalDate date){
        List<Commande> CommandeParDate = commandeRepository.findByDateCommande(date);
                return CommandeParDate;
    }



}
