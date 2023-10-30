package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DTO.*;
import tn.esprit.spring.DTO.WrapperFLLP;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.IDeliveryService;
import tn.esprit.spring.interfaces.IPaiementService;
import tn.esprit.spring.repositories.*;
import tn.esprit.spring.services.EmailServiceHoussem;
import tn.esprit.spring.services.LpanierImpl;
import tn.esprit.spring.services.MailService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/billing")
public class PaiementRestController {
	@Autowired
    IDeliveryService deliveryService;
	@Autowired
	IPaiementService paiementService;
	@Autowired
	private MailService mailService;
	@Autowired
	private FactureRepository factureRepository;
	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	private LivreurRepository livreurRepository;
	@Autowired
	private DeliveryRepository deliveryRepository;
	@Autowired
	private LpanierImpl lpanierService;
	@Autowired
	private PaiementRepository paiementRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailServiceHoussem esh;

	@PostMapping("/checkout/{factureId}")
	public void addPaiement(@RequestBody Paiement paiement,
							@PathVariable("factureId") Integer factureId) throws IOException {
        paiementService.addPaiement(paiement,factureId);
		//mailService.sendTextEmail(facture);
	}

	@PostMapping("/checkout-final/{userId}")
	public CheckoutDTO checkout(@RequestBody WrapperFLLP wrapper,
								@PathVariable("userId") String userID) throws IOException {
		CheckoutDTO chd = new CheckoutDTO();
		List<ArticleCommandeDTO> acdList = new ArrayList<>();
		Float prix_total=0f;
		User user = userRepository.findByUserName(userID);
		Facture facture = new Facture();
		List<LignePanier> validatedLines = lpanierService.ValidateOrderByUser(user.getUserName());

		Integer commandeId = validatedLines.get(0).getStatus();
		for(LignePanier lp : validatedLines){
			log.info(lp.getLignePanierId()+"\n");
			ArticleCommandeDTO acd = new ArticleCommandeDTO();
			acd.setQuantite(lp.getQuantite());
			acd.setNomArticle(lp.getArticle().getNomArticle());
			acd.setPrixArticle(lp.getArticle().getPrixHT());
			acdList.add(acd);
			prix_total=prix_total+lp.getArticle().getPrixHT()*lp.getQuantite();
			if(lp.getArticle().getPromotion()!=null){
				facture.setSolde(lp.getArticle().getPromotion());
			}else{facture.setSolde((float) 0);}
		}
		Commande commande = commandeRepository.findById(commandeId).orElse(null);
		facture.setPrixHT(prix_total);
		facture.setPrixTTC((float) (prix_total*1.19));
		Paiement paiement = wrapper.getPaiement();
		Livraison livraison = wrapper.getLivraison();
		//Livreur livreur = wrapper.getLivreur();
		facture.setCommandeId(commande);
		facture.setEtatFacture(0);
		facture.setDateFacturation(LocalDate.now());
		facture = factureRepository.save(facture);
		//livraison.setFactureId(facture);
		CheckStockDTO csd = new CheckStockDTO();
		csd.setLivraison(livraison);
		csd.setFactureId(facture.getFactureId());
		csd.setCommandeId(commandeId);
		csd.setLpList(validatedLines);
		deliveryService.addDelivery(csd);
		deliveryRepository.save(livraison);
		//livreurRepository.save(livreur);
		
		paiement.setFactureId(facture);
		paiement.setDatePaiement(LocalDate.now());
		paiementRepository.save(paiement);
		chd.setCommandeId(commandeId);
		chd.setAdresseLivraison(livraison.getAdresseLivraison());
		chd.setDatelivraisonPrevu(livraison.getDateLivraisonPrevu());
		chd.setNom_prenom(user.getUserFirstName()+" "+user.getUserLastName());
		chd.setEmail(user.getUserEmail());
		chd.setPhone(user.getUserPhone());
		chd.setPrixTotal(prix_total);
		chd.setArticleCommandeList(acdList);
		esh.send(chd);
		//mailService.sendTextEmail(chd);
		return chd;
		//mailService.sendTextEmail(facture);
	}
/*
	@GetMapping("/payments")
	public ResponseEntity<List<Paiement>> getPayments() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("Authorization", "Bearer xyz123");
		headers.set("Access-Control-Allow-Origin", "http://localhost:4200");
		return ResponseEntity.ok().headers(headers).body(paiementService.getPaiement());
	}
*/

	@GetMapping("/payments")
	public List<PaiementFrontDTO> getPayments() {
		List<PaiementFrontDTO> lpd = new ArrayList<>();
		List<Paiement> listP = paiementService.getPaiement();
		for(Paiement p : listP){
			Commande cmd = new Commande();
			cmd=commandeRepository.findById(p.getFactureId().getCommandeId().getCommandeId()).orElse(null);
			User user = new User();
			user = userRepository.findByUserName(cmd.getUserId().getUserName());
			PaiementFrontDTO pd = new PaiementFrontDTO();
			pd.setPaiementId(p.getPaiementId());
			pd.setCvv(p.getCvv());
			pd.setDatePaiement(p.getDatePaiement());
			pd.setEtatPaiement(p.getEtatPaiement());
			pd.setCardNumber(p.getCardNumber());
			pd.setClientName(user.getUserFirstName()+' '+user.getUserLastName());
			pd.setMethodePaiement(p.getMethodePaiement());
			pd.setExpirationDate(p.getExpirationDate());
			pd.setFactureId(p.getFactureId().getFactureId());
			pd.setCvv(p.getCvv());
			lpd.add(pd);
		}
		return lpd;
	}

	@GetMapping("/bill/{factureId}")
	public Paiement getPaiementByFactureId(@PathVariable("factureId") Integer factureId) {
		return paiementService.getPaiementByFacture(factureId);
	}

	@GetMapping("/bill/user/{userId}")  ///tested
	public List<Paiement> getPaiementByUserId(@PathVariable("userId") String userID) {
		return paiementService.getPaiementsByUser(userID);
	}

	@GetMapping("/bill-by-date/{dateFrom}/{dateTo}")
	public List<Paiement> getPaiementByDate(@PathVariable("dateFrom") String dateFrom,
											  @PathVariable("dateTo") String dateTo) {
		return paiementService.getPaiementsByDate(dateFrom,dateTo);
	}

	@GetMapping("/bill-by-card/{cardNumber}")
	public List<Paiement> getPaiementByCard(@PathVariable("cardNumber") Integer cardNumber) {
		return paiementService.getPaiementsByCardNumber(cardNumber);
	}

	@GetMapping("/reverted-payments")
	public List<Paiement> getPaiementRevertedPayments() {
		return paiementService.getRevertedPaiements();
	}
}


