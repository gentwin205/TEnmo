package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferPackage {

    private int senderId;
    private int recipientId;
    private BigDecimal amount;

    public TransferPackage(int senderId, int recipientId, BigDecimal amount) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
    }

    public TransferPackage() {
        //nothing
    }

    public int getSenderId() {
        return senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
