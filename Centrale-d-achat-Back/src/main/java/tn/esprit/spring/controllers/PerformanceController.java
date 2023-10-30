package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Performance;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.EmployeeRepository;
import tn.esprit.spring.repositories.PerformanceRepository;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.EmailService;
import tn.esprit.spring.services.PerformanceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    @Autowired
    private PerformanceImpl performanceService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PerformanceRepository performanceRepository;

    @GetMapping("/{employeeId}/{rating}")
    public ResponseEntity<Void> addPerformanceToEmployee(@PathVariable("employeeId") Integer employeeId,
                                                         @PathVariable("rating") Double performanceRating) {
        performanceService.addPerformanceToEmployee(employeeId, performanceRating);
        Employee employee = employeeRepository.findById(Long.valueOf(employeeId)).orElse(null);
        //System.out.println(employee.toString());
        User user = userRepository.findByUserName(employee.getUser().getUserName());
        performanceService.updateSalaryForExcellentPerformance(employeeId);
        emailService.send("houssem.hosni@esprit.tn", buildEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Void> updateSalaryForExcellentPerformance(@PathVariable Integer employeeId) {
        performanceService.updateSalaryForExcellentPerformance(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Performance>> getAllPerformancesForEmployee(@PathVariable Integer employeeId) {
        List<Performance> performances = performanceService.getAllPerformancesForEmployee(employeeId);
        return new ResponseEntity<>(performances, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public void deletePerformance(@PathVariable("employeeId") Integer employeeId){
        performanceRepository.deleteById(employeeId);
    }

    private String buildEmail() {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Évaluation et avis</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"></p><p style=\\\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> </p><p style=\\\\\\\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"></p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"\"></a> </p></blockquote>\n  <p>Félicitations! Nous tenons à vous informer que votre travail acharné a été reconnu et que vous avez été choisi pour recevoir une augmentation de salaire. Nous sommes fiers de votre contribution à notre entreprise et nous espérons que vous continuerez à exceller dans votre travail. Encore une fois, félicitations et continuez à être un atout précieux pour notre équipe!</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
