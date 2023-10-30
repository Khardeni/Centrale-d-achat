package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Livraison;
import tn.esprit.spring.entities.LivraisonLivreur;
import tn.esprit.spring.entities.Livreur;
import tn.esprit.spring.interfaces.IDeliveryService;
import tn.esprit.spring.interfaces.ILLService;
import tn.esprit.spring.interfaces.ILivreurService;
import tn.esprit.spring.repositories.DeliveryRepository;
import tn.esprit.spring.repositories.LivraisonLivreurRepository;
import tn.esprit.spring.repositories.LivreurRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/enlevement")
public class LLRestController {
	@Autowired
	ILLService livraisonLivreurService;
	@Autowired
	ILivreurService livreurService;
	@Autowired
	IDeliveryService deliveryService;

	@PostMapping("/assign-delivery/{livraisonId}") 						//tested
	public void assignDelivery(@PathVariable("livraisonId") Integer livraisonId,
							   @RequestBody Livreur livreur){
        livraisonLivreurService.assignLivraison(livreur,livraisonId);
		deliveryService.setDeliveryToShipping(livraisonId);
	}

	@DeleteMapping("/unassign/{llId}")
	public void unassignDelivery(@PathVariable("llId") Integer llId){
		livraisonLivreurService.unassign(llId);
	}

	@GetMapping("/assigned-deliveries/{livreurId}")
	public List<LivraisonLivreur> getAssignedDeliveriesByLivreur(@PathVariable("livreurId") Integer livreurId){
		return livraisonLivreurService.getAssignedByLivreur(livreurId);
	}
}


