package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface AccountDao {

    BigDecimal getBalanceById(int id);

    BigDecimal subtractBalanceById(int id, BigDecimal amount);

    BigDecimal addBalanceById(int id, BigDecimal amount);

    int getAccountByUserId(int id);


}
