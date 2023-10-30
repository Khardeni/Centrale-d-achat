package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Conge;
import tn.esprit.spring.interfaces.ICongeService;

import java.util.List;


@RestController
    @RequestMapping("/Conge")
    public class CongeRestController {
        @Autowired
        ICongeService congeService;

        ///POST
        @PostMapping(value="/accept")
        public ResponseEntity<Conge> acceptConge(@RequestBody Conge conge) {
            try {
                congeService.acceptConge(conge);
                return new ResponseEntity<Conge>(conge, HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }

        }
        @PostMapping("/refuseConge/{conge_id}")
        public void refuseConge(@PathVariable Integer conge_id, String message) {
            congeService.refuseConge(conge_id,message);
        }



            @GetMapping(value = "/list")
        public List<Conge> userlist() { //  @RequestBody User user : eli jeni mel front

            return congeService.allConges();
        }

        @GetMapping (value = "/findConge/{conge_id}")
        public ResponseEntity<Conge> findConge (@PathVariable Integer conge_id) {
            try {
                Conge c =  congeService.findConge(conge_id);

                return new ResponseEntity<Conge>(c, HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }



        @DeleteMapping (value = "/deleteConge/{conge_id}")
        public ResponseEntity<Void> deleteConge (@PathVariable Integer conge_id) {
            try {
                congeService.findConge(conge_id);
                congeService.deleteConge(conge_id);

                return new ResponseEntity<Void>(HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }

        @PostMapping(value = "/addConge")
        public ResponseEntity<Conge> addConge(@RequestBody Conge conge) { //  @RequestBody Conge conge : eli jeni mel front

            try {
                Conge c = congeService.addConge(conge);
                return new ResponseEntity<Conge>(HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }

        @PutMapping(value = "/updateConge")
        public ResponseEntity<Void> updateConge(@RequestBody Conge conge) { //  @RequestBody Conge conge : eli jeni mel front

            try {
                congeService.addConge(conge);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }


        @GetMapping (value = "/findCongesById/{user_id}")
        public ResponseEntity<List<Conge>> findAbsencesById (@PathVariable Integer user_id) {
            try {
                List<Conge> c =  congeService.allCongeById(user_id);

                return new ResponseEntity<List<Conge>>(c, HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }

}
