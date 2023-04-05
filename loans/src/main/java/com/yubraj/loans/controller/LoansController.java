package com.yubraj.loans.controller;

import com.yubraj.loans.model.Customer;
import com.yubraj.loans.model.Loans;
import com.yubraj.loans.repository.LoansRepository;
import java.util.List;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

  @Autowired private LoansRepository loansRepository;

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
}
