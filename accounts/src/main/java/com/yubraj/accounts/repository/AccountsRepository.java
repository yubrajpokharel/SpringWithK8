package com.yubraj.accounts.repository;

import com.yubraj.accounts.models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Integer> {
  Accounts findByCustomerId(int customerId);
}
