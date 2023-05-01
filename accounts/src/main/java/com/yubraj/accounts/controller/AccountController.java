package com.yubraj.accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.yubraj.accounts.config.AccountsConfig;
import com.yubraj.accounts.models.Accounts;
import com.yubraj.accounts.models.Cards;
import com.yubraj.accounts.models.Customer;
import com.yubraj.accounts.models.CustomerDetails;
import com.yubraj.accounts.models.Loans;
import com.yubraj.accounts.models.Properties;
import com.yubraj.accounts.repository.AccountsRepository;
import com.yubraj.accounts.service.client.CardsFeignClient;
import com.yubraj.accounts.service.client.LoansFeignClient;
import java.util.List;
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
  private final CardsFeignClient cardsFeignClient;
  private final LoansFeignClient loansFeignClient;

  public AccountController(
      AccountsRepository accountsRepository,
      AccountsConfig accountsConfig,
      CardsFeignClient cardsFeignClient,
      LoansFeignClient loansFeignClient) {
    this.accountsRepository = accountsRepository;
    this.accountsConfig = accountsConfig;
    this.cardsFeignClient = cardsFeignClient;
    this.loansFeignClient = loansFeignClient;
  }

  @PostMapping("/myAccount")
  public ResponseEntity<?> getAccountDetails(@RequestBody Customer customer) {

    Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
    if (null == accounts) {
      return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
  }

  @GetMapping("/config/accounts")
  public ResponseEntity<?> getAccountConfigs() throws JsonProcessingException {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    Properties properties =
        new Properties(
            accountsConfig.getMsg(),
            accountsConfig.getBuildVersion(),
            accountsConfig.getMailDetails(),
            accountsConfig.getActiveBranches());
    String response = ow.writeValueAsString(properties);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/myCustomerDetails")
  public CustomerDetails myCustomerDetails(@RequestBody Customer customer) {
    Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
    List<Loans> loans = loansFeignClient.getLoansDetails(customer);
    List<Cards> cards = cardsFeignClient.getCardDetails(customer);

    CustomerDetails customerDetails =
        CustomerDetails.builder().accounts(accounts).loans(loans).cards(cards).build();

    return customerDetails;
  }
}
