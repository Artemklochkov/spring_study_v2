package com.example.payment.payment.services;

import com.example.payment.payment.interfaces.AccountRepositoryInterface;
import com.example.payment.payment.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TransferServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void transferMoneyHappyFlow() {
        AccountRepositoryInterface accountRepository =
                BDDMockito.mock(AccountRepositoryInterface.class);
        TransferService transferService =
                new TransferService(accountRepository);

        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        Account destination = new Account();
        destination.setId(2);
        destination.setAmount(new BigDecimal(1000));

        BDDMockito.given(accountRepository.findById(sender.getId()))
                .willReturn(Optional.of(sender));

        BDDMockito.given(accountRepository.findById(destination.getId()))
                .willReturn(Optional.of(destination));

        transferService.transferMoney(sender.getId(), destination.getId(), new BigDecimal(100));

        BDDMockito.verify(accountRepository).changeAmount(1, new BigDecimal(900));
        BDDMockito.verify(accountRepository).changeAmount(2, new BigDecimal(1100));

    }
}