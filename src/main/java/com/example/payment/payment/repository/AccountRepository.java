package com.example.payment.payment.repository;

import com.example.payment.payment.model.Account;
import com.example.payment.payment.rowMapers.AccountRowMapper;
import org.postgresql.jdbc.PreferQueryMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class AccountRepository {
    private final JdbcTemplate jdbc;

    public AccountRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Account findAccountById(long id) {
        String sql = "SELECT * FROM account where id=?";

        return jdbc.queryForObject(sql, new AccountRowMapper(), id);
    }

    public void changeAmount(long id, BigDecimal amount) {
        String sql = "UPDATE account SET amount=? WHERE id=?";
        jdbc.update(sql, amount, id);
    }

    public List<Account> getAllAccounts(){
        String sql = "SELECT * FROM account";
        return jdbc.query(sql, new AccountRowMapper());
    }
}
