package com.yubraj.loans.repository;

import com.yubraj.loans.model.Loans;
import java.util.List;import org.springframework.data.jpa.repository.JpaRepository;

public interface LoansRepository extends JpaRepository<Loans, Integer> {
  List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);
}
