package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.JwtRequest;
import tn.esprit.spring.entities.JwtResponse;
import tn.esprit.spring.entities.UserUnlockRequest;
import tn.esprit.spring.services.User.JwtService;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
    @PutMapping({"/unlockAccount"})
    public void unlockAccount(@RequestBody UserUnlockRequest userUnlockRequest){
        jwtService.unlockAccount(userUnlockRequest);
    }

}
