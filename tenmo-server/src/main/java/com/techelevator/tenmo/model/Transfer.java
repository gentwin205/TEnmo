package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private int accountFrom;
    private int accountTo;
    private int transferId;
    private int transferTypeId;
    private int transferStatusId;
    private String transferTypeDesc;
    private String transferStatusDesc;
    private String userFrom;
    private String userTo;
    private BigDecimal amount;

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }
}
