package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Offre;
import tn.esprit.spring.entities.OffreDemande;
import tn.esprit.spring.interfaces.IOffreDemande;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/demande-offre")
public class OffreDemandeController  {
    IOffreDemande iOffreDemande;

    @PostMapping("/add-demande-offre")
    @ResponseBody
    public void addDemandeoffre(@RequestBody OffreDemande offreDemande){
        iOffreDemande.addOffreDemande(offreDemande);
    }
    @PutMapping("/edit-demande-offre")
    @ResponseBody
    public OffreDemande editDemandeOffre(@RequestBody OffreDemande offreDemande){
        return   iOffreDemande.editOffreDemande(offreDemande);
    }

    @DeleteMapping("/delete-demande-offre/{id-demande-offre}")
    @ResponseBody
    public String deleteOffre(@PathVariable("id-demande-offre") Integer id){
        iOffreDemande.deleteOffreDemande(id);
        return "Entry Deleted";
    }

    @GetMapping("/get-all-demande-offres")
    public List<OffreDemande> getAllOffers(){
        return iOffreDemande.getAllOffresDemande();
    }

    @GetMapping("/get-demande-offre-by-id/{id-demande-offre}")
    public OffreDemande getOffreById(@PathVariable("id-demande-offre") Integer id){
        return iOffreDemande.getOffreDemandeById(id);
    }



    }
