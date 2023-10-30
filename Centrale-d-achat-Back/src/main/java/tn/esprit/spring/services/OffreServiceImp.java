package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Offre;
import tn.esprit.spring.interfaces.IOffre;
import tn.esprit.spring.repositories.OffreRepository;

import javax.mail.MessagingException;
import java.util.List;


@Slf4j
@Service
public class OffreServiceImp  implements IOffre {

    OffreRepository offreRepository;
    private JavaMailSender javaMailSender;

    EmailService emailService;

    public OffreServiceImp(OffreRepository offreRepository, JavaMailSender javaMailSender, EmailService emailService) {
        this.offreRepository = offreRepository;
        this.javaMailSender = javaMailSender;
        this.emailService = emailService;
    }

    @Override
    public void addOffre(Offre offre) {
        offre.setDateOffre(offre.getDateOffre());
        offre.setDescOffre(offre.getDescOffre());
        offre.setEtatOffre(offre.getEtatOffre());
        offreRepository.save( offre);
        try {
            emailService.sendOfferEmail("hamdi.adam@esprit.tn", offre.getDescOffre(), offre.getEtatOffre(),offre.getDateOffre(),offre.getOffreId());
        } catch (MessagingException e) {
            log.error("Failed to send email", e);
        }
    }

    @Override
    public Offre editOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    @Override
    public String deleteOffre(Integer id) {
        offreRepository.deleteById(id);
        return "Entry deleted";
    }

    @Override
    public List<Offre> getAllOffres() {

        return offreRepository.findAll();
    }

    @Override
    public Offre getOffreById(Integer id) {
        return offreRepository.findById(id).get();
    }
}
