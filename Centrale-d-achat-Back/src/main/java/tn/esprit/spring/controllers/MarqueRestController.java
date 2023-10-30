package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.CategorieArticle;
import tn.esprit.spring.entities.Marque;
import tn.esprit.spring.interfaces.ICategorieService;
import tn.esprit.spring.interfaces.IMarqueService;
import tn.esprit.spring.repositories.MarqueRepository;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/marque")
public class MarqueRestController {
	@Autowired
    IMarqueService iMarqueService;
    @Autowired
    private MarqueRepository marqueRepository;


    @PostMapping("/addmarque")											//tested
    public void addMarque(@RequestBody Marque marque) {
        iMarqueService.addMarque(marque);
    };
    @DeleteMapping("/delete-marque/{idmarque}")
    public void deleteMarque(@PathVariable("idmarque") Integer idMarque){
        Marque marque = marqueRepository.findById(idMarque).orElse(null);
        iMarqueService.deleteMarque(marque);
    }
    @GetMapping("/liste-marque")
    public List<Marque> getAllMarques() {
        return iMarqueService.getAllMarques();
    }

    @PutMapping  ("/update-marque/{idMarque}")
    public void update(@RequestBody Marque marque,
                       @PathVariable("idMarque") Integer marqueId){
        iMarqueService.updateMarque(marque,marqueId);
    }
    @PostMapping("/{id}/image")
    public ResponseEntity<String> uploadImage(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
        try {
             iMarqueService.uploadImage(file, id);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }
}


