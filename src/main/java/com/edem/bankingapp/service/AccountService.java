package com.edem.bankingapp.service;

import com.edem.bankingapp.Dto.AccountDto;
import com.edem.bankingapp.entites.Account;


public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);
}
