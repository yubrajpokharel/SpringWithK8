package com.yubraj.accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.yubraj.accounts.config.AccountsConfig;
import com.yubraj.accounts.models.Accounts;
import com.yubraj.accounts.models.Customer;
import com.yubraj.accounts.models.Properties;import com.yubraj.accounts.repository.AccountsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

  private final AccountsRepository accountsRepository;
  private final AccountsConfig accountsConfig;

  public AccountController(AccountsRepository accountsRepository, AccountsConfig accountsConfig) {
    this.accountsRepository = accountsRepository;
  this.accountsConfig = accountsConfig;}

  @PostMapping("/myAccount")
  public ResponseEntity<?> getAccountDetails(@RequestBody Customer customer) {

    Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
    if(null == accounts) {
      return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

  }

  @GetMapping("/config/accounts")
  public ResponseEntity<?> getAccountConfigs()throws JsonProcessingException{
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    Properties properties = new Properties(accountsConfig.getMsg(), accountsConfig.getBuildVersion(), accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
    String response = ow.writeValueAsString(properties);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
