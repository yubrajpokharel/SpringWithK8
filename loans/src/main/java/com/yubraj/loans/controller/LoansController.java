package com.yubraj.loans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;import com.fasterxml.jackson.databind.ObjectMapper;import com.fasterxml.jackson.databind.ObjectWriter;import com.yubraj.loans.config.LoansConfig;import com.yubraj.loans.model.Customer;
import com.yubraj.loans.model.Loans;
import com.yubraj.loans.model.Properties;import com.yubraj.loans.repository.LoansRepository;
import java.util.List;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

  @Autowired private LoansRepository loansRepository;
  @Autowired private LoansConfig loansConfig;

  @PostMapping("/myLoans")
  public List<Loans> getLoansDetails(@RequestBody Customer customer) {
    List<Loans> loans =
        loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
    if (loans != null) {
      return loans;
    } else {
      return null;
    }
  }

  @GetMapping("/config/loans")
  public ResponseEntity<?> getAccountConfigs()throws JsonProcessingException{
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    Properties properties = new Properties(loansConfig.getMsg(), loansConfig.getBuildVersion(), loansConfig.getMailDetails(), loansConfig.getActiveBranches());
    String response = ow.writeValueAsString(properties);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
