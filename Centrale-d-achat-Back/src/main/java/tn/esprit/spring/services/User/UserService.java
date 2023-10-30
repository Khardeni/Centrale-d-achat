package tn.esprit.spring.services.User;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.IUserService;
import tn.esprit.spring.repositories.RoleRepository;
import tn.esprit.spring.repositories.UserRepository;


import javax.mail.MessagingException;
import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tn.esprit.spring.services.User.Mail.EmailServiceImpl;
import tn.esprit.spring.services.User.Twilio.SmsServiceImpl;
import tn.esprit.spring.util.UserCode;
import tn.esprit.spring.util.QrCodeGenerator;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userDao;

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SmsServiceImpl smsService;

    @Autowired
    private EmailServiceImpl emailServ;

    private QrCodeGenerator qrCodeGenerator;

    private final String imagePath = "././src/main/resources/qrcodes/QRCode.png";

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role FournisseurRole = new Role();
        FournisseurRole.setRoleName("Fournisseur");
        FournisseurRole.setRoleDescription("Fournisseur role");
        roleDao.save(FournisseurRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setIsverified(1);
        adminUser.setIsLocked(0);
        adminUser.setTwoFactorAuth(0);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);
    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setIsverified(0);
        user.setIsLocked(0);
        user.setTwoFactorAuth(0);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        try {
            emailServ.sendVerificationEmail(user);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return userDao.save(user);
    }

    public User activateUser(String token) {
        User user = userDao.findByVerificationToken(token);
        if (user != null) {
            user.setIsverified(1);
            user.setVerificationToken(null);
            userDao.save(user);
        }
        return user;
    }

    public String Activate2factor (String userName)
    {
        User user = userDao.findByUserName(userName);
        user.setTwoFactorAuth(1);
        user.setIsLocked(1);
        userDao.save(user);
        return ("2 Factor Authentifcation Activated");
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<User> getAll(){
        return userDao.findAll();
    }
    public User findOne(String userName){
        return userDao.findById(userName).orElse(null);
    }

    public Boolean delete(String userName){
        User u= userDao.findById(userName).orElse(null);
        if (u != null) {
        u.getRole().clear();
        userDao.delete(u);
        return true;
        }
        else return false;
    }
    public void update(User user){
        userDao.save(user);
    }

    public void addRoleToUser(String roleName, String user)
    {
        Role r = roleDao.findById(roleName).orElse(null);
        User u= userDao.findById(user).orElse(null);
        Set<Role> userRoles = u.getRole();
        userRoles.add(r);
        u.setRole(userRoles);
        userDao.save(u);
    }

    public void removeRoleFromUser(String role){}
    @Transactional

    public long count(){
        long count=userDao.count();
        return count;
    }

    public long countFournisseur(){
        long countFournisseur=0;
        List<User> users=userDao.findAll();
        for(User user:users) {

            Set<Role> roles=user.getRole();
            Role role= roles.iterator().next();
            String rolename = role.getRoleName();
            if(rolename.equals("Fournisseur")){
                countFournisseur+=1;
            }
        }
        return countFournisseur;
    }
    public long countadmin(){
        long countadmin=0;
        List<User> users=userDao.findAll();
        for(User user:users) {

            Set<Role> roles=user.getRole();
            Role role= roles.iterator().next();
            String rolename = role.getRoleName();
            if(rolename.equals("Admin")){
                countadmin+=1;
            }
        }
        return countadmin;
    }

    // Reset Password:
    public boolean ifEmailExist(String UserEmail){
        return userDao.existsByUserEmail(UserEmail);
    }

    @Transactional
    public String getPasswordByUserEmail(String userEmail){
        return userDao.getPasswordByUserEmail(userEmail);
    }

    public User findByUserEmail(String UserEmail)
    {
        return this.userDao.findByUserEmail(UserEmail);
   }

    public User findByPhone(String phone)
    {
        return this.userDao.findByUserPhone(phone);
    }

    public User findByUserName(String username)
    {
        return this.userDao.findByUserName(username);
    }
    public void editUser(User user){
        this.userDao.save(user);
    }

    public UserAccountResponse resetPasswordEmail(UserResetPassword resetPassword) {
        User user = this.findByUserEmail(resetPassword.getEmail());
        UserAccountResponse accountResponse = new UserAccountResponse();
        if (user != null) {
            String code = UserCode.getCode();
            System.out.println("le code est" + code);
            UserMail mail = new UserMail(resetPassword.getEmail(), code);
            System.out.println("le mail est" + resetPassword.getEmail());
            System.out.println("la variable mail est" + mail);
            this.emailServ.sendCodeByMail(mail);
            System.out.println("la variable User est" + user);
            user.setUserCode(code);
            userDao.save(user);
            accountResponse.setResult(1);
        } else {
            accountResponse.setResult(0);
        }
        return accountResponse;
    }

    public UserAccountResponse resetPassword(UserNewPassword newPassword){
        User user = this.findByUserEmail(newPassword.getEmail());
        System.out.println("la variable User est " + user);
        UserAccountResponse accountResponse = new UserAccountResponse();
        System.out.println("la variable entry est " + user.getCode() + "////:" + user.getUserPassword());

        if(user != null){
            if(user.getUserCode().equals(newPassword.getCode())){
                user.setUserPassword(passwordEncoder.encode(newPassword.getPassword()));
                System.out.println("la variable entry est " + user.getCode() + "////:" + user.getUserPassword());
                userDao.save(user);
                accountResponse.setResult(1);
            } else {
                accountResponse.setResult(0);
            }
        } else {
            accountResponse.setResult(0);
        }
        return accountResponse;
    }
//reset password Using SMS:

    //Check Phone number in DB and send 6 digits code.
    public UserAccountResponse CheckSMS (UserResetPasswordSMS userResetPasswordSMS) {
        // Retrieve user using the entered phone number.
        User user = this.findByPhone(userResetPasswordSMS.getPhone());
        System.out.println("la variable User est " + user);
        System.out.println("la variable Phone est " + userResetPasswordSMS.getPhone());
        UserAccountResponse accountResponse = new UserAccountResponse();
        if (user != null) {
            String code = UserCode.getSmsCode();
            System.out.println("le code est" + code);
            this.smsService.SendSMS(userResetPasswordSMS.getPhone(),code);
            System.out.println("la variable User est" + user);
            user.setUserCode(code);
            userDao.save(user);
            accountResponse.setResult(2);
        } else {
            accountResponse.setResult(3);
        }
        return accountResponse;
    }

    //Compare given code with the one stored in DB and reset password.
    public UserAccountResponse resetPasswordSMS(UserNewPasswordSMS userNewPasswordSMS) {
        User user = this.findByPhone(userNewPasswordSMS.getPhone());
        UserAccountResponse accountResponse = new UserAccountResponse();
        if(user != null){
            if(user.getUserCode().equals(userNewPasswordSMS.getCode())){
                user.setUserPassword(passwordEncoder.encode(userNewPasswordSMS.getPassword()));
                user.setUserCode("expired");
                userDao.save(user);
                accountResponse.setResult(1);
            } else {
                accountResponse.setResult(0);
            }
        } else {
            accountResponse.setResult(0);
        }
        return accountResponse;
    }
    public String callwithQrCode (String username) {
        User user = this.findByUserName(username);
        QrCodeRequest qrcoderequest = new QrCodeRequest();
        qrcoderequest.setPhone(user.getUserPhone());
        qrCodeGenerator.generateImageQRCode(QrCodeRequest.getPhone(), 250, 250, imagePath);
        return ("Qr code generated succeffully");
    }
}
