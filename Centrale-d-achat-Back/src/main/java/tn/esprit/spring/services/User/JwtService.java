package tn.esprit.spring.services.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.JwtRequest;
import tn.esprit.spring.entities.JwtResponse;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.UserUnlockRequest;
import tn.esprit.spring.interfaces.IUserService;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.User.Twilio.SmsServiceImpl;
import tn.esprit.spring.util.JwtUtil;
import tn.esprit.spring.util.UserCode;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SmsServiceImpl smsService;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        User user = userDao.findById(userName).get();
        JwtResponse jwtResponse = new JwtResponse(user, newGeneratedToken);
        if (user != null) {
            if (user.getIsverified() == 1) {
                if (user.getTwoFactorAuth() == 0 && user.getIsLocked() == 0) {
                    return (jwtResponse);
                } else if (user.getTwoFactorAuth() == 1 && user.getIsLocked() == 1) {
                    String code = UserCode.getSmsCode();
                    System.out.println("le code est" + code);
                    this.smsService.SendSMS(user.getUserPhone(), code);
                    System.out.println("la variable User est" + user);
                    user.setUserCode(code);
                    userDao.save(user);
                    System.out.println("Check your SMS");

                }
                else if (user.getTwoFactorAuth() == 1 && user.getIsLocked() == 0) {
                    user.setIsLocked(1);
                    userDao.save(user);
                    return (jwtResponse);
                }
                System.out.println("Contact admin");
                return null;
            }
            System.out.println("Please verify your account first");
            return null;

        }
        System.out.println("Password or username incorrect");
        return null;

    }

    public String unlockAccount (UserUnlockRequest userUnlockRequest) {
        User user = userDao.findById(userUnlockRequest.getUsername()).get();
        if(user.getUserCode().equals(userUnlockRequest.getCode())) {
            user.setIsLocked(0);
            userDao.save(user);
            return ("your account " + user.getUserName() + " has been unlocked");
        }
        else return ("Wrong code, please try again");
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findById(username).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
