package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
    List<Transfer> getAllTransfers(int userId);
    Transfer sendTransfers(BigDecimal amount, int accountFrom, int accountTo);
}
