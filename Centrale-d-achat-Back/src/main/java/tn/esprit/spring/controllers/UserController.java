package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.IUserService;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.User.Mail.EmailServiceImpl;
import tn.esprit.spring.services.User.UserService;
import tn.esprit.spring.services.User.Verif.VerificationTokenService;
import tn.esprit.spring.util.UserCode;

import javax.annotation.*;
import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private EmailServiceImpl emailServ;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenService verificationTokenService;


    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userDao;

   @PostConstruct()
   public void initRoleAndUser() {
       userService.initRoleAndUser();
   }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        User savedUser = userService.registerNewUser(user);
        VerificationToken verificationToken = verificationTokenService.createVerificationToken(user); // création du jeton de vérification
        verificationTokenService.saveVerificationToken(verificationToken);
        return savedUser;
    }
    @PutMapping("/activate/{verificationToken}")
    public ResponseEntity activateAccount(@PathVariable String verificationToken) {
        User user = userService.activateUser(verificationToken);
        if (user != null) {
            String to = user.getUserEmail();
            String subject = "Account Created";
            String text = "Your account has been created successfully.";
            try {
                emailServ.sendEmail(to, subject);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return new ResponseEntity<>(HttpStatus.OK);
            // return ("Congratulations " + user.getUserName() + " Your account has been activated successfully");
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            //return ("there was an error verifying your account, please make sure you have entered the right token and that the token hasn't expried");
        }
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/CallQr/{userName}"})
    @PreAuthorize("hasRole('User')")
    public String callwithQrCode(@PathVariable String userName){
        return userService.callwithQrCode(userName);
    }

    @GetMapping({"/users"})
    @PreAuthorize("hasRole('Admin')")
    public List<User> getAll(){
        return userService.getAll();
    }
    @GetMapping({"/user/{userName}"})
    public User findOne(@PathVariable String userName){
        return userService.findOne(userName);
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @DeleteMapping ({"/delete/{userName}"})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity delete(@PathVariable String userName){
        if (userService.delete(userName)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
            //return ("User " +userName+ "was deleted successfully ");}
        else             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            // return ("User "+userName+ " does not exist, please check the Username again");
    }

    @PutMapping  ({"/update"})
    @PreAuthorize("hasRole('User')")
    public void update(@RequestBody User user){
        userService.update(user);
    }
    @PutMapping  ({"/Activate2FA/{userName}"})
    @PreAuthorize("hasRole('User')")
    public void update(@PathVariable String userName){
        userService.Activate2factor(userName);
    }
    @GetMapping("/count")
    @PreAuthorize("hasRole('Admin')")
    public long count(){return userService.count();}
    @GetMapping("/countoperateur")
    @PreAuthorize("hasRole('Admin')")
    public long countFournisseur(){return userService.countFournisseur();}
    @GetMapping("/countadmin")
    @PreAuthorize("hasRole('Admin')")
    public long countadmin(){return userService.countadmin();}

    @PutMapping  ({"/addRole/{roleName}/{userName}"})
    @PreAuthorize("hasRole('Admin')")
    public void addRoleToUser(@PathVariable String roleName,@PathVariable String userName) {
        userService.addRoleToUser(roleName,userName);
    }
    // http://localhost:8080/checkEmail
    @PostMapping("/checkEmail")
    public UserAccountResponse resetPasswordEmail(@RequestBody UserResetPassword resetPassword){
        return userService.resetPasswordEmail(resetPassword);
    }
    // http://localhost:8080/resetPassword
    @PostMapping("/resetPassword")
    public UserAccountResponse resetPassword(@RequestBody UserNewPassword newPassword){
        return userService.resetPassword(newPassword);
    }
    @PostMapping("/checkSMS")
    public UserAccountResponse CheckSMS (@RequestBody UserResetPasswordSMS userResetPasswordSMS) {
        return userService.CheckSMS(userResetPasswordSMS);
    }
    @PostMapping("/resetPasswordSMS")
    public UserAccountResponse resetPasswordSMS (@RequestBody UserNewPasswordSMS newPassword) {
        return userService.resetPasswordSMS(newPassword);
    }


}
