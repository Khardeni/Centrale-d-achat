package tn.esprit.spring.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Offre;
import tn.esprit.spring.interfaces.IOffre;
import tn.esprit.spring.services.EmailService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/offre")
public class OffreController {

    IOffre iOffre;

    private EmailService emailService;




    @PostMapping("/add-offre")
    @ResponseBody
    public void addoffre(@RequestBody Offre offre)  {
        System.out.println(offre.toString());
        iOffre.addOffre(offre);
    }

    @PutMapping("/edit-offre")
    @ResponseBody
      public Offre editOffre(@RequestBody Offre offre){
      return   iOffre.editOffre(offre);
    }

    @DeleteMapping("/delete-offre/{id-offre}")
    @ResponseBody
    public String deleteOffre(@PathVariable("id-offre") Integer id){
        iOffre.deleteOffre(id);
        return "Entry Deleted";
    }

    @GetMapping("/get-all-offres")
    public List<Offre> getAllOffers(){
        return iOffre.getAllOffres();
    }

    @GetMapping("/get-offre-by-id/{id-offre}")
    public Offre getOffreById(@PathVariable("id-offre") Integer id){
        return iOffre.getOffreById(id);
    }


}
