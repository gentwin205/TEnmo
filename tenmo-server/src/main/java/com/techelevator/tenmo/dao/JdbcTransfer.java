package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransfer implements TransferDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTransfer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Transfer> getAllTransfers(int userId) {
        List<Transfer> list = new ArrayList<>();
        String sql = null;
        return null;
    }

    @Override
    public Transfer createTransfer(BigDecimal amount, int accountFrom, int accountTo, int transferType, int transferStatus) {
        Transfer transfer = new Transfer(amount, accountFrom, accountTo);
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount)" +
                "VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, transferType, transferStatus, accountFrom, accountTo, amount);
        return transfer;


    }


}
