package com.example.payment.payment.services;

import com.example.payment.payment.customExeptions.AccountNotFoundException;
import com.example.payment.payment.interfaces.AccountRepositoryInterface;
import com.example.payment.payment.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTestWithAnnotations {
    @Mock
    private AccountRepositoryInterface accountRepository;
    @InjectMocks
    private TransferService transferService;

    @Test
    @DisplayName("transfer money OK")
    public void transferMoneyHappyFlow(){
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        Account destination = new Account();
        destination.setId(2);
        destination.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(sender.getId()))
                .willReturn(Optional.of(sender));

        given(accountRepository.findById(destination.getId()))
                .willReturn(Optional.of(destination));

        transferService.transferMoney(sender.getId(), destination.getId(),new BigDecimal(100));

        verify(accountRepository).changeAmount(1,new BigDecimal(900));
        verify(accountRepository).changeAmount(2,new BigDecimal(1100));
    }

    @Test
    @DisplayName(" transfer destination NOT FOUND")
    public void transferDestinationNotFound() {
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(1L))
                .willReturn(Optional.of(sender));

        given(accountRepository.findById(2L))
                .willReturn(Optional.empty());

        Assertions.assertThrows(AccountNotFoundException.class,
                () -> transferService.transferMoney(1,2, new BigDecimal(100)));

        verify(accountRepository, never()).changeAmount(anyLong(),any());
    }
}
