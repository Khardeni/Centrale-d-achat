package tn.esprit.spring.interfaces;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import tn.esprit.spring.DTO.CheckoutDTO;

public interface EmailSender {


    void send(CheckoutDTO checkoutDTO);

    void send (String to , String email);
}
