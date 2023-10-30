package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DTO.CommandeHistorique;
import tn.esprit.spring.DTO.LignePanierDto;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.ILpanierService;
import tn.esprit.spring.interfaces.IRetourService;
import tn.esprit.spring.repositories.CommandeRepository;
import tn.esprit.spring.repositories.FactureRepository;
import tn.esprit.spring.repositories.PanierRepository;
import tn.esprit.spring.repositories.UserRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/lignep")
public class LignePanierRestController {
	@Autowired
    ILpanierService lpanierService;

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private UserRepository userRepository;

    /* HOUSSEM WROTE THIS METHOD */
    @GetMapping("/get-history/{factureId}")
    public List<CommandeHistorique> houssemMazelYlawej3alLista(@PathVariable("factureId") Integer factureId){
        Facture facture = new Facture();
        facture = factureRepository.findById(factureId).orElse(null);
        Commande commande = new Commande();
        commande = commandeRepository.findById(facture.getCommandeId().getCommandeId()).orElse(null);
        return lpanierService.houssemYlawej3laLista(commande.getCommandeId());
    }
    @GetMapping("/cart/val/{username}")
    public List<CommandeHistorique> getCheckoutDetails(@PathVariable("username") String username){
        User user = userRepository.findByUserName(username);
        Panier panier = panierRepository.findByUserId(user);

        return lpanierService.getCheckoutDetails(panier.getPanierId());
    }
    @GetMapping("/cart/{username}")
    public List<CommandeHistorique> getLPbyUsername(@PathVariable("username") String username){
        User user = userRepository.findByUserName(username);
        Panier panier = panierRepository.findByUserId(user);

        return lpanierService.getLPbyPanier(panier.getPanierId());
    }
    @PostMapping("/addlp")
    public void addlp(@RequestBody LignePanierDto lignePanierDto){
lpanierService.AddArticleToLigne(lignePanierDto);    }

    @DeleteMapping("/deletelp/{idpanier}")
    public void deletelp(@PathVariable("idpanier") Integer idp){

        lpanierService.deleteByPanier(idp);
    }



    @GetMapping("/update-status/{idlp}")
    public void updateStatus(@PathVariable("idlp") Integer idlp){

        lpanierService.updateStatus(idlp);
    }

    @DeleteMapping("/deletelpid/{idlp}")
    public void deleteLignePanier(@PathVariable("idlp") Integer lpId) {
        lpanierService.deleteLignePanierById(lpId);
    }



}


