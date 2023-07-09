package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
        String sql = "SELECT t.*, u1.username AS userFrom, u2.username AS userTo" +
                "FROM transfer t" +
                "JOIN account a ON account_from = a.account_id" +
                "JOIN account b ON account_to = b.account_id" +
                "JOIN tenmo_user u1 ON a.user_id = u1.user_id" +
                "JOIN tenmo_user u2 ON b.user_id = u2.user_id" +
                "WHERE a.user_id = ? OR b.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
        while(results.next()){
            Transfer transfer = mapRowToTransfer(results);
            list.add(transfer);
        }
        return list;
    }

    @Override
    public Transfer createTransfer(BigDecimal amount, int accountFrom, int accountTo, int transferType, int transferStatus) {
        Transfer transfer = new Transfer(amount, accountFrom, accountTo);
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount)" +
                "VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, transferType, transferStatus, accountFrom, accountTo, amount);
        return transfer;


    }

    private Transfer mapRowToTransfer(SqlRowSet results){
        Transfer transfer = new Transfer();
        transfer.setUserTo(results.getString("user_to"));
        transfer.setUserFrom(results.getString("user_from"));
        transfer.setTransferTypeDesc(results.getString("transfer_type_desc"));
        transfer.setTransferStatusDesc(results.getString("transfer_status_desc"));
        transfer.setTransferStatusId(results.getInt("transfer_status_id"));
        transfer.setTransferTypeId(results.getInt("transfer_type_id"));
        transfer.setTransferId(results.getInt("transfer_id"));
        transfer.setAccountFrom(results.getInt("account_from"));
        transfer.setAccountTo(results.getInt("account_to"));
        transfer.setAmount(results.getBigDecimal("amount"));
        return transfer;
    }


}
