package com.yubraj.accounts.controller;

import com.yubraj.accounts.models.Accounts;
import com.yubraj.accounts.models.Customer;
import com.yubraj.accounts.repository.AccountsRepository;
import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

  private final AccountsRepository accountsRepository;

  public AccountController(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
  }

  @PostMapping("/myAccount")
  public ResponseEntity<?> getAccountDetails(@RequestBody Customer customer) {

    Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
    if(null == accounts) {
      return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

  }
}
