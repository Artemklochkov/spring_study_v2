package com.example.payment.payment.controller;

import com.example.payment.payment.model.Account;
import com.example.payment.payment.model.TransferRequest;
import com.example.payment.payment.services.TransferService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountController {
    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    private final TransferService transferService;

    @PostMapping("/transfer")
    public void transfer(
            @RequestBody TransferRequest request
            ) {
        transferService.transferMoney(request.getSenderId(),
                            request.getReceiverId(),
                            BigDecimal.valueOf(request.getAmount()));
    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(
            @RequestParam(required = false) String name
    ){
        if (name!=null) {
            return transferService.findAllAccountsByName(name);
        }
        else {
            return transferService.getAllAccounts();
        }
    }
}
