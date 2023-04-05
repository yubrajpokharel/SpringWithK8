package com.yubraj.cards.repository;

import com.yubraj.cards.model.Cards;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface CardsRepository extends CrudRepository<Cards, Long> {

  List<Cards> findByCustomerId(int customerId);

}
