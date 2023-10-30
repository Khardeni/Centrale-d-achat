package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Impot;
import tn.esprit.spring.interfaces.IArticleService;
import tn.esprit.spring.interfaces.IImpotService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Impot")
public class ImpotRestController {
    @Autowired
    IImpotService impotService;
    @Autowired
    IArticleService articleService;


    ///POST
    @PostMapping("/add-Impot")         ///tested
    @ResponseBody
    public Impot addImpot(@RequestBody Impot impot) {
        return impotService.addImpot(impot);
    }

    @PostMapping("/deactivate-Impot/{impotId}")          ///tested
    public Impot deactivateImpot(@PathVariable("impotId") Integer impotId) {
        return impotService.deactivateImpot(impotId);
    }
    ///GET
    @GetMapping("/Get-All-Impots")                 ///tested
    public List<Impot> getAllImpots() {
        return impotService.findAllImpots();
    }
    @GetMapping("/Get-Impot-ById/{id}")                    ///tested
    public ResponseEntity<Impot> getImpotById(@PathVariable Integer id) {
        Optional<Impot> impot = impotService.findImpotById(id);
        if (impot.isPresent()) {
            return ResponseEntity.ok(impot.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    ///PUT
    @PutMapping("/Update-Impot/{id}")                 ///tested
    public ResponseEntity<Impot> updateImpot(@PathVariable Integer id, @RequestBody Impot updatedImpot) {
        Optional<Impot> existingImpot = impotService.findImpotById(id);
        if (existingImpot.isPresent()) {
            updatedImpot.setImpotId(id);
            return ResponseEntity.ok(impotService.updateImpot(id, updatedImpot));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    ///DELETE
    @DeleteMapping("/delete-Impot/{id}")                   ///tested
    public ResponseEntity<Void> deleteImpot(@PathVariable Integer id) {
        Optional<Impot> existingImpot = impotService.findImpotById(id);
        if (existingImpot.isPresent()) {
            impotService.deleteImpotById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
     /*   @PostMapping("/addImpot-Article/{articleId}/{impotId}")
        public void addImpotToArticle(@PathVariable("articleId") Integer articleId,@PathVariable("impotId") Integer impotId) {
           articleService.addImpotToArticle(articleId,impotId);
        }*/




    }

