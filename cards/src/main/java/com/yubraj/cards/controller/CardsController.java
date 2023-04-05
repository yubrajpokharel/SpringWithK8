package com.yubraj.cards.controller;

import com.yubraj.cards.model.Cards;
import com.yubraj.cards.model.Customer;
import com.yubraj.cards.repository.CardsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

  @Autowired private CardsRepository cardsRepository;

  @PostMapping("/myCards")
  public List<Cards> getCardDetails(@RequestBody Customer customer) {
    List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
    if (cards != null) {
      return cards;
    } else {
      return null;
    }
  }
}
