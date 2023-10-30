package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Commande;
import tn.esprit.spring.entities.LignePanier;
import tn.esprit.spring.services.CommandeImpl;
import tn.esprit.spring.services.LpanierImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderRestController {

    @Autowired
    private LpanierImpl lpanierService;
    @Autowired
    private CommandeImpl commandeService;

    @PostMapping("/validateOrder/{userId}")
    public List<LignePanier> validateOrderByUser(@PathVariable String userID) {
        List<LignePanier> validatedLines = lpanierService.ValidateOrderByUser(userID);
        return validatedLines;
    }

    @GetMapping("/get-by-user/{username}")
    public List<Commande> getOrdersList(@PathVariable("username") String username) {
        List<Commande> listc = commandeService.GetCommandeByUser(username);
        return listc;
    }
}
