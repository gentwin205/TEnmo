package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer { // need something for status not sure if int or string
    private BigDecimal moneyTransfer;
    private String sendUser;
    private String receiveUser;
    private int sendId;
    private int toId;

    public BigDecimal getMoneyTransfer() {
        return moneyTransfer;
    }

    public void setMoneyTransfer(BigDecimal moneyTransfer) {
        this.moneyTransfer = moneyTransfer;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    private int transferId;

}
