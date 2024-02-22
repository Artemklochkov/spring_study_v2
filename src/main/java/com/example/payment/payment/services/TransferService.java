package com.example.payment.payment.services;

import com.example.payment.payment.model.Account;
import com.example.payment.payment.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {
    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        //Аккаунты получатель/отправитель
        Account sender = accountRepository.findAccountById(idSender);
        Account receiver = accountRepository.findAccountById(idReceiver);

        //Новые суммы на счетах
        BigDecimal newAmountSender = sender.getAmount().subtract(amount);
        BigDecimal newAmountReceiver = receiver.getAmount().add(amount);

        //Меняем Сумму на счетах
        accountRepository.changeAmount(idSender, newAmountSender);
        accountRepository.changeAmount(idReceiver, newAmountReceiver);
        //throw new RuntimeException("Oh no, something go wrong");
    }

    public List<Account> getAllAccounts(){
        return accountRepository.getAllAccounts();
    }
}
