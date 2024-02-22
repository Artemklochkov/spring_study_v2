package com.example.payment.payment.repository;

import com.example.payment.payment.model.Purchase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseRepository {
private final JdbcTemplate jdbcTemplate;

    public PurchaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void storePurchase(Purchase purchase){
        String sql = "INSERT INTO purchase(product,price) VALUES(?, ?)";

        jdbcTemplate.update(sql,purchase.getProduct(),purchase.getPrice());
    }

    public List<Purchase> findAllPurchase(){
        String sql = "SELECT * FROM purchase";

        RowMapper<Purchase> purchaseRowMapper = (rs, rowNum) -> {
            Purchase rowObj = new Purchase();
            rowObj.setId(rs.getInt("id"));
            rowObj.setProduct(rs.getString("product"));
            rowObj.setPrice(rs.getBigDecimal("price"));
            return rowObj;
        };
        return jdbcTemplate.query(sql, purchaseRowMapper);
    }
}
