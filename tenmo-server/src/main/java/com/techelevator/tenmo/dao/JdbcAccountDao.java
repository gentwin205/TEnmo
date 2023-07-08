package com.techelevator.tenmo.dao;

import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal getBalanceById(int id) {
        BigDecimal balance = null;
        String sql = "SELECT balance FROM account WHERE user_id = ?;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            if (result.next()) {
                balance = result.getBigDecimal("balance");
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Couldn't connect to database.");
        }

        return balance;
    }

    @Override
    public BigDecimal subtractBalanceById(int id, BigDecimal amount) { //input user id
        String sql = "UPDATE account SET balance = ? WHERE user_id = ?;";
        BigDecimal currentBalance = getBalanceById(id);
        BigDecimal newBalance = currentBalance.subtract(amount);

        //Check if amount given is less than current balance
        if (newBalance.doubleValue() > 0) {
            try {
                jdbcTemplate.update(sql, newBalance.doubleValue(), id);
            } catch (CannotGetJdbcConnectionException e) {
                System.out.println("Can't Connect.");
            }
            return newBalance;
        }
        return null;
    }

    @Override
    public BigDecimal addBalanceById(int id, BigDecimal amount) { //input user id
        String sql = "UPDATE account SET balance = ? WHERE user_id = ?;";
        BigDecimal currentBalance = getBalanceById(id);
        BigDecimal newBalance = currentBalance.add(amount);

        try {
            jdbcTemplate.update(sql, newBalance.doubleValue(), id);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Can't Connect.");
        }

        return newBalance;
    }

    @Override
    public int getAccountByUserId(int id) {
        String sql = "SELECT account_id FROM account WHERE user_id = ?;";
        int accountId = 0;

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            if (result.next()) {
                accountId = result.getInt("account_id");
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Can't connect.");
        }

        return accountId;
    }
}
