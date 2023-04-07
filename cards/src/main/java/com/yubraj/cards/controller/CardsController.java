package com.yubraj.cards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.yubraj.cards.config.CardsConfig;
import com.yubraj.cards.model.Cards;
import com.yubraj.cards.model.Customer;
import com.yubraj.cards.model.Properties;
import com.yubraj.cards.repository.CardsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

  @Autowired private CardsRepository cardsRepository;
  @Autowired private CardsConfig cardsConfig;

  @PostMapping("/myCards")
  public List<Cards> getCardDetails(@RequestBody Customer customer) {
    List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
    if (cards != null) {
      return cards;
    } else {
      return null;
    }
  }

  @GetMapping("/config/cards")
  public ResponseEntity<?> getAccountConfigs() throws JsonProcessingException {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    Properties properties =
        new Properties(
            cardsConfig.getMsg(),
            cardsConfig.getBuildVersion(),
            cardsConfig.getMailDetails(),
            cardsConfig.getActiveBranches());
    String response = ow.writeValueAsString(properties);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
