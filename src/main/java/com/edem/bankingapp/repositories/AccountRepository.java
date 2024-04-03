package com.edem.bankingapp.repositories;

import com.edem.bankingapp.entites.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Long, Account> {

}
