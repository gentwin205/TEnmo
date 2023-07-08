package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferPackage {

    private int senderId;
    private int recipientId;
    private double amount;

    public TransferPackage(int senderId, int recipientId, double amount) {
        //BigDecimal might cause problems in http body
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public double getAmount() {
        return amount;
    }
}
