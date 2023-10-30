package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DTO.LivraisonDetailleDTO;
import tn.esprit.spring.entities.Livraison;
import tn.esprit.spring.interfaces.IDeliveryService;
import tn.esprit.spring.interfaces.ILpanierService;
import tn.esprit.spring.repositories.CommandeRepository;
import tn.esprit.spring.repositories.DeliveryRepository;
import tn.esprit.spring.repositories.FactureRepository;
import tn.esprit.spring.services.MailService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/delivery")
public class DeliveryRestController {
	@Autowired
    IDeliveryService deliveryService;

	@Autowired
	private MailService mailService;
	@Autowired
	private FactureRepository factureRepository;
	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	ILpanierService lpanierService;
	@Autowired
	private DeliveryRepository deliveryRepository;

	/*@PostMapping("/add-delivery/{factureId}/{commandeId}")
	public void addDelivery(@RequestBody Livraison livraison,
							@PathVariable("factureId") Integer factureId,
							@PathVariable("commandeId") Integer commandeId) throws IOException {
        deliveryService.addDelivery(CheckStockDTO());
		Facture facture = factureRepository.findById(factureId).orElse(null);
		Commande commade = commandeRepository.findById(commandeId).orElse(null);
		mailService.sendMailTest();
	}*/

    @PostMapping("/edit-delivery/{livraisonId}")
    public void editDelivery(@RequestBody Livraison livraison,
                             @PathVariable("livraisonId") Integer livraisonId){
        deliveryService.EditDelivery(livraison,livraisonId);
    }

	@GetMapping("/set-shipping/{livraisonId}")
	public void setDeliveryToShipping(@PathVariable("livraisonId") Integer livraisonId){
		deliveryService.setDeliveryToShipping(livraisonId);
	}

	@GetMapping("/set-shipped/{livraisonId}")
	public void setDeliveryToShipped(@PathVariable("livraisonId") Integer livraisonId){
		deliveryService.setDeliveryToShipped(livraisonId);
	}
	@GetMapping("/deliveries")
	public List<Livraison> getDeliveries() {
		return deliveryService.getDeliveries();
	}
	@GetMapping("/pending")
	public Integer getPendingDeliveries() {
		return deliveryService.getPendingDeliveries();
	}
	@GetMapping("/late")
	public Integer getLateDeliveries() {
		return deliveryService.getLateDeliveries();
	}
	@GetMapping("/details/{livraisonId}")
	public LivraisonDetailleDTO getDeliveryDetails(@PathVariable("livraisonId") Integer livraisonId) {
		return deliveryService.getDeliveryDetails(livraisonId);
	}
	@GetMapping("/history/{username}")
	public List<LivraisonDetailleDTO> getDeliveryHistory(@PathVariable("username") String username) {
		return deliveryService.getDeliveryHistory(username);
	}

	@GetMapping("/delivery/{livraisonId}")
	public Livraison getDeliveryById(@PathVariable("livraisonId") Integer livraisonId) {
		return deliveryService.getDeliveryById(livraisonId);
	}

	@GetMapping("/get-deliveries-by-date/{dateFrom}/{dateTo}")
	public List<Livraison> getDeliveriesByDate(@PathVariable("dateFrom") String dateFrom,
                                    @PathVariable("dateTo") String dateTo) {
		return deliveryService.getDeliveriesByDate(dateFrom,dateTo);
	}

}


