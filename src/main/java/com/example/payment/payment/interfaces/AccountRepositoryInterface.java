package com.example.payment.payment.interfaces;

import com.example.payment.payment.model.Account;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepositoryInterface extends CrudRepository<Account, Long> {
    @Query("SELECT * FROM account WHERE name = :name")
    List<Account> findAccountsByName(String name);

    //Account findAccountById(long id);
  /*  @Query("SELECT * FROM account")
    List<Account> getAllAccounts();*/

    @Modifying
    @Query("UPDATE account SET amount=:amount where id=:id")
    void changeAmount(long id, BigDecimal amount);
}
