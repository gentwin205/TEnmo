package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
    List<Transfer> getAllTransfers(int userId);

    Transfer getTransferById(int transferId);
    Transfer createTransfer(BigDecimal amount, int accountFrom, int accountTo, int transferType, int transferStatus);
}
