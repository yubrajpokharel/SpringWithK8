package com.yubraj.accounts.service.client;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.yubraj.accounts.models.Customer;
import com.yubraj.accounts.models.Loans;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("loans")
public interface LoansFeignClient {

  @RequestMapping(method = RequestMethod.POST, value = "myLoans", consumes = APPLICATION_JSON_VALUE)
  List<Loans> getLoansDetails(@RequestBody Customer customer);
}
