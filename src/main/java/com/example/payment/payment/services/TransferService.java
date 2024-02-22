package com.example.payment.payment.services;

import com.example.payment.payment.customExeptions.AccountNotFoundException;
import com.example.payment.payment.interfaces.AccountRepositoryInterface;
import com.example.payment.payment.model.Account;
import com.example.payment.payment.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {
    private final AccountRepositoryInterface accountRepository;

    public TransferService(AccountRepositoryInterface accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        //Аккаунты получатель/отправитель
//        Account sender = accountRepository.findAccountById(idSender);
        Account sender = accountRepository.findById(idSender).orElseThrow(
                AccountNotFoundException::new);
//        Account receiver = accountRepository.findAccountById(idReceiver);
        Account receiver = accountRepository.findById(idReceiver)
                .orElseThrow(AccountNotFoundException::new);

        //Новые суммы на счетах
        BigDecimal newAmountSender = sender.getAmount().subtract(amount);
        BigDecimal newAmountReceiver = receiver.getAmount().add(amount);

        //Меняем Сумму на счетах
        accountRepository.changeAmount(idSender, newAmountSender);
        accountRepository.changeAmount(idReceiver, newAmountReceiver);
        //throw new RuntimeException("Oh no, something go wrong");
    }

    public Iterable<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public List<Account> findAllAccountsByName(String name) {
        return accountRepository.findAccountsByName(name);
    }
}
