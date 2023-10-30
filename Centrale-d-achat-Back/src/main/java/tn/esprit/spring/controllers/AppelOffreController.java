package tn.esprit.spring.controllers;


import com.itextpdf.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import org.xhtmlrenderer.pdf.ITextRenderer;
import tn.esprit.spring.entities.AppelOffre;

import tn.esprit.spring.interfaces.IAppelOffre;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/appel-offre")
public class AppelOffreController {

    private SpringTemplateEngine templateEngine;
    IAppelOffre iAppelOffre;

    @PostMapping("/add-appel-offre")
    @ResponseBody
    public void addAppelOffre(@RequestBody AppelOffre appelOffre){
        iAppelOffre.addAppelOffre(appelOffre);

    }

    @GetMapping(value = "/appel-offre-pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateAppelOffrePdf(@PathVariable("id") Integer id) throws IOException, DocumentException, com.lowagie.text.DocumentException {
        AppelOffre appelOffre = iAppelOffre.getAppelOffreById(id);
        Context context = new Context();
        context.setVariable("appelOffre", appelOffre);
        String html = templateEngine.process("appel-offre-template", context);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "appelOffre_" + appelOffre.getAppelOffreId() + ".pdf";
        headers.setContentDispositionFormData(filename, filename);
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }





    @PutMapping("/edit-appel-offre")
    @ResponseBody
    public AppelOffre editAppelOffre(@RequestBody AppelOffre appelOffre){
      return   iAppelOffre.editAppelOffre(appelOffre);
    }

    @DeleteMapping("/delete-appel-offre/{id-appel-offre}")
    @ResponseBody
    public String deleteAppelOffre(@PathVariable("id-appel-offre") Integer id ){
        iAppelOffre.deleteAppelOffre(id);
        return "Entry Deleted";
    }

    @GetMapping("/get-all-appel-offre")
    /* @ResponseBody Dont need this were only fetching from database*/
    public List<AppelOffre> getAppelOffres(){
        return iAppelOffre.getAllAppelsOffre();

    }
    @GetMapping("/get-appel-offre-by-id/{id-appel-offre}")
    public AppelOffre getAppelOffreById(@PathVariable("id-appel-offre") Integer id){
        return  iAppelOffre.getAppelOffreById(id);
    }


}
