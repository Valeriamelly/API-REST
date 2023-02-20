package com.example.apitransfer239919.controller;

import com.example.apitransfer239919.exception.ResourceNotFoundException;
import com.example.apitransfer239919.exception.ValidationException;
import com.example.apitransfer239919.model.Account;
import com.example.apitransfer239919.model.Transfer;
import com.example.apitransfer239919.repository.AccountRepository;
import com.example.apitransfer239919.repository.TransferRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
public class TransferController {
    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;

    public TransferController(TransferRepository transferRepository, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }
    @PostMapping("/transfers")
    @Transactional
    public ResponseEntity<Transfer> save(@RequestParam("sourceAccountId") Long sourceAccountId,
                                         @RequestParam("destinationAccountId") Long destinationAccountId,
                                         @RequestParam("amount") BigDecimal amount,
                                         @RequestParam("currency") String currency
    )throws IOException {

        Transfer transfer = new Transfer();
        transfer.setAmount(amount);
        transfer.setCurrency(currency);
        transfer.setStatus("success");
        transfer.setCreatedAt(LocalDateTime.now());


        Account sourceAccount = accountRepository.findById(sourceAccountId)
                .orElse(null);
        if( sourceAccount!=null) {
            transfer.setSourceAccount(sourceAccount);
        }

        Account destinationAccount = accountRepository.findById(destinationAccountId)
                .orElse(null);
        if( sourceAccount!=null) {
            transfer.setDestinationAccount(destinationAccount);
        }


        Transfer transferSaved=transferRepository.save(transfer);
        validateTransfer(transfer);

        return new ResponseEntity<Transfer>(transferSaved, HttpStatus.CREATED);
    }
    @GetMapping("/transfers/{transferId}")
    @Transactional (readOnly = true)
    public ResponseEntity<Transfer> searchById(@PathVariable  Long transferId){
        Transfer transfer=transferRepository.findById(transferId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found product with id="+transferId));


        return new ResponseEntity<Transfer>(transfer,HttpStatus.OK);
    }
    public void validateTransfer(Transfer transfer){
        if(!transfer.getCurrency().equals("EUR") && !transfer.getCurrency().equals("USD") && !transfer.getCurrency().equals("GBP")){
            throw new ValidationException("La moneda debe ser EUR, USD o GBP");
        }
        //cuando el amount a transferir es cero
        if(transfer.getAmount().compareTo(BigDecimal.ZERO)==0){
            throw new ValidationException("El monto a transferir debe ser mayor a cero");
        }
        //cuando el monto a transferir supera el monto de
        //la cuenta origen.
        if(transfer.getAmount().compareTo(transfer.getSourceAccount().getBalance())>0){
            throw new ValidationException("El saldo de la cuenta de origen es insuficiente para realizar la transferencia");
        }


    }





}
