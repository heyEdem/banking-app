package com.edem.bankingapp.service.Impl;

import com.edem.bankingapp.Dto.AccountDto;
import com.edem.bankingapp.entites.Account;
import com.edem.bankingapp.mapper.AccountMapper;
import com.edem.bankingapp.repositories.AccountRepository;
import com.edem.bankingapp.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        log.info("created account {}",accountDto.getId());
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account =
                accountRepository.findById(id)
                        .orElseThrow(()->new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        return null;
    }
    //todo DEPOSIT METHOD
}
