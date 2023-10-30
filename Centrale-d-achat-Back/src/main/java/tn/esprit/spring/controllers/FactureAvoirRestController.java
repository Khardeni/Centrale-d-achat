package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.FactureAvoir;
import tn.esprit.spring.interfaces.IDeliveryService;
import tn.esprit.spring.interfaces.IFactureAvoirService;
import tn.esprit.spring.interfaces.IFactureService;
import tn.esprit.spring.interfaces.IRetourService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/facture-avoir")
public class FactureAvoirRestController {

	@Autowired
	IFactureService FactureImpl;
	@Autowired
	IFactureAvoirService FactureAvoirImpl;

	@Autowired
	IDeliveryService DeliveryImpl;

	@Autowired
	IRetourService RetourImpl;

	@PostMapping("/add-facture-avoir/{factureId}")
	public void addFactureAvoir(@RequestBody FactureAvoir factureAvoir,
								@PathVariable("factureId") Integer factureId) {
		FactureAvoirImpl.addFactureAvoir(factureAvoir,factureId);
	}

	@GetMapping("/get-facture-avoir-by-date/{dateFrom}/{dateTo}")
	public List<FactureAvoir> getFacturesAvoirByDate(@PathVariable("dateFrom") String dateFrom,
										   @PathVariable("dateTo") String dateTo) {
		return FactureAvoirImpl.getFacturesAvoirByDate(dateFrom, dateTo);
	}

	@GetMapping("/count-facture-avoir-by-date/{dateFrom}/{dateTo}")
	public Integer countFactureAvoirByCommande(@PathVariable("dateFrom") String dateFrom,
											   @PathVariable("dateTo") String dateTo){
		return FactureAvoirImpl.countFacturesAvoirByDate(dateFrom,dateTo);
	}

	@GetMapping("/factures-avoir")
	public List<FactureAvoir> getFactures() {
		return FactureAvoirImpl.getFacturesAvoir();
	}

}


