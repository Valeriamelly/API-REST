package com.example.apitransfer239919.controller;

import com.example.apitransfer239919.exception.ValidationException;
import com.example.apitransfer239919.model.Account;
import com.example.apitransfer239919.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private AccountRepository accountRepository;
    public AccountController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {

        return new ResponseEntity<List<Account>>(accountRepository.findAll(), HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        validateAccount(account);
        return new ResponseEntity<Account>(accountRepository.save(account), HttpStatus.CREATED);
    }

    public void validateAccount(Account account){
        if(account.getBalance() == null ){
            throw new ValidationException("El balance de la cuenta debe ser obligatorio");
        }
        //currency debe ser EUR, USD, GBP
        if(!account.getCurrency().equals("EUR") && !account.getCurrency().equals("USD") && !account.getCurrency().equals("GBP")){
            throw new ValidationException("La moneda debe ser EUR, USD o GBP");
        }

    }




}
