package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Livreur;
import tn.esprit.spring.interfaces.IDeliveryService;
import tn.esprit.spring.interfaces.ILivreurService;
import tn.esprit.spring.repositories.DeliveryRepository;
import tn.esprit.spring.repositories.LivreurRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tracking")
public class LivreurRestController {
	@Autowired
	ILivreurService livreurService;

	@Autowired
	IDeliveryService deliveryService;

	@Autowired
	private DeliveryRepository deliveryRepository;
	@Autowired
	private LivreurRepository livreurRepository;

	@PostMapping("/add-livreur/") 						//tested
	public void addLivreur(@RequestBody Livreur livreur){
        livreurService.addLivreur(livreur);
	}


    @PostMapping("/edit-livreur/{livreurId}")						//tested
    public void editDelivery(@RequestBody Livreur livreur,
                             @PathVariable("livreurId") Integer livreurId){
        livreurService.editLivreur(livreur,livreurId);
    }

	@DeleteMapping("/d/{livreurId}")					//tested
	public void deleteLivreur(@PathVariable("livreurId") Integer livreurId) {
		livreurService.deleteLivreur(livreurId);
	}

	@GetMapping("/nom/{nomLivreur}")					//tested
	public List<Livreur> trackByNomLivreur(@PathVariable("nomLivreur") String nomLivreur) {
		return livreurService.trackDeliveriesByNomLivreur(nomLivreur);
	}

	@GetMapping("/getall")					//tested
	public List<Livreur> getAll() {
		return livreurRepository.findAll();
	}
	@GetMapping("/societe/{societe}")					//tested
	public List<Livreur> trackBySociete(@PathVariable("societe") String societe) {
		return livreurService.trackDeliveriesBySociete(societe);
	}

	@GetMapping("/livraison/{livraisonId}")   				//tested
	public Livreur trackByLivraison(@PathVariable("livraisonId") Integer livraisonId) {
		return livreurService.trackDeliveryByLivraison(livraisonId);
	}

	@GetMapping("/date/{date}")
	public List<Livreur> trackByDate(@PathVariable("date") String date) {
		return livreurService.trackDeliveriesByDate(date);
	}

}


