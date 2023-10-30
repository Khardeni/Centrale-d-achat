package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.DemandeAchat;
import tn.esprit.spring.interfaces.IDemandeAchat;
import tn.esprit.spring.repositories.DemandeAchatRepository;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DemandeAchatImp implements IDemandeAchat {

    DemandeAchatRepository demandeAchatRepository;
    @Override
    public void addDemandeAchat(DemandeAchat demandeAchat) {

        demandeAchat.setTitreDA(demandeAchat.getTitreDA());
        demandeAchat.setDescDA(demandeAchat.getDescDA());
        demandeAchat.setStatusDA(demandeAchat.getStatusDA());
        demandeAchat.setDateExpiration(demandeAchat.getDateExpiration());
        demandeAchat.setDatePublication(demandeAchat.getDatePublication());
        demandeAchatRepository.save(demandeAchat);

    }

    @Override
    public DemandeAchat editDemandeAchat(DemandeAchat demandeAchat) {
        return demandeAchatRepository.save(demandeAchat);
    }

    @Override
    public String deleteDemandeAchat(Integer id) {
        demandeAchatRepository.deleteById(id);
        return "Entry Deleted";
    }

    @Override
    public List<DemandeAchat> getAllDemandesAchat() {
        return  demandeAchatRepository.findAll();
    }

    @Override
    public DemandeAchat getDemandeAchatById(Integer id) {
        return demandeAchatRepository.findById(id).get();
    }
}
