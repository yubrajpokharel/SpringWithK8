package com.yubraj.accounts.service.client;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.yubraj.accounts.models.Cards;
import com.yubraj.accounts.models.Customer;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("cards")
public interface CardsFeignClient {

  @RequestMapping(method = RequestMethod.POST, value = "myCards", consumes = APPLICATION_JSON_VALUE)
  List<Cards> getCardDetails(@RequestBody Customer customer);
}
