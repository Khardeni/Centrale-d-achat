package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DTO.FactureDetailDTO;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.FactureAvoir;
import tn.esprit.spring.interfaces.IFactureService;
import tn.esprit.spring.repositories.DeliveryRepository;
import tn.esprit.spring.repositories.FactureRepository;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/facture")
public class FactureRestController {
	@Autowired
	IFactureService FactureImpl;
	@Autowired
	private DeliveryRepository deliveryRepository;
	@Autowired
	private FactureRepository factureRepository;


	@PostMapping("/add-facture/{commandeId}")											//tested
	public void addFacture(@RequestBody Facture facture,
									 @PathVariable("commandeId") Integer commandeId) throws IOException {
		//mailingFacture.sendEmail("houssemhosni13@gmail.com","test","test");
		System.out.print("test");
/*
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("Authorization", "Bearer xyz123");
		headers.set("Access-Control-Allow-Origin", "*");*/
		FactureImpl.addFacture(facture,commandeId);
		//ResponseEntity.ok().headers(headers).body(null);

	};

	@GetMapping("/get-facture-by-date/{dateFrom}/{dateTo}")					//tested
	public List<Facture> getFacturesByDate(@PathVariable("dateFrom") String dateFrom,
										   @PathVariable("dateTo") String dateTo) {
		return FactureImpl.getFacturesByDate(dateFrom, dateTo);
	}

	@GetMapping("/get-facture-by-commande/{commandeId}")					//tested
	public Facture getFactureByCommande(@PathVariable("commandeId") Integer commandeId){
		return FactureImpl.getFactureByCommande(commandeId);
	}


	@GetMapping("/revenue/{year}")					//tested
	public List<Double> getRevenueAnnuelle(@PathVariable("year") Integer year){
		return FactureImpl.getAnnualRevenue(year);
	}

	@GetMapping("/get-facture-by-user/{userId}")							//tested
	public List<Facture> getFactureByUser(@PathVariable("userId") String userId){
		return FactureImpl.getFacturesByUser(userId);
	}

	@GetMapping("/detail/{factureId}")							//tested
	public FactureDetailDTO getFactureDetails(@PathVariable("factureId") Integer factureId){
		return FactureImpl.getFactureDetail(factureId);
	}

	@GetMapping("/get-facture-by-facture-avoir")
	public Facture getFactureByFactureAvoir(@RequestBody FactureAvoir factureAvoir){
		return FactureImpl.getFactureByFactureAvoir(factureAvoir);
	}

	@DeleteMapping("/delete/{factureId}")
	public void deleteFacture(@PathVariable("factureId") Integer factureId){
		factureRepository.deleteById(factureId);
	}

	@GetMapping("/factures")
	public List<Facture> getFactures() {
		return FactureImpl.getFactures();
	}

}


