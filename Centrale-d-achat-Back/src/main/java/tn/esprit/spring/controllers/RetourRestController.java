package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Retour;
import tn.esprit.spring.interfaces.IDeliveryService;
import tn.esprit.spring.interfaces.IFactureAvoirService;
import tn.esprit.spring.interfaces.IRetourService;
import tn.esprit.spring.repositories.FactureAvoirRepository;
import tn.esprit.spring.repositories.UserRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/retour")
public class RetourRestController {
	@Autowired
    IRetourService retourService;
    @Autowired
    IDeliveryService deliveryService;
    @Autowired
    IFactureAvoirService factureAvoirService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FactureAvoirRepository factureAvoirRepository;

    @GetMapping("/cancel-delivery/{livraisonId}")
    public void cancelDelivery(@PathVariable("livraisonId") Integer livraisonId){
        retourService.CancelDelivery(livraisonId);
    }
    @GetMapping("/canceled-deliveries")
    public List<Retour> getCanceledDeliveries() {
        return retourService.getCanceledDeliveries();
    }


}
