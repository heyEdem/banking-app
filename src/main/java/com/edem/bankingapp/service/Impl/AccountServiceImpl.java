package com.edem.bankingapp.service.Impl;

import com.edem.bankingapp.Dto.AccountDto;
import com.edem.bankingapp.entites.Account;
import com.edem.bankingapp.mapper.AccountMapper;
import com.edem.bankingapp.repositories.AccountRepository;
import com.edem.bankingapp.service.AccountService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        account.setCreatedAt(ZonedDateTime.now());
        Account savedAccount = accountRepository.save(account);
        log.info("created account {}",accountDto.getId());
        return AccountMapper.mapToAccountDto(savedAccount);
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
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("account not found"));


         account.setBalance(amount + account.getBalance());
         Account savaedAccount = accountRepository.save(account);
         return AccountMapper.mapToAccountDto(savaedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found"));

        if (account.getBalance() < amount)
            throw new RuntimeException("Insufficient balance");

        account.setBalance(account.getBalance() - amount);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        return allAccounts.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't exist"));
        accountRepository.delete(account);
    }
}
