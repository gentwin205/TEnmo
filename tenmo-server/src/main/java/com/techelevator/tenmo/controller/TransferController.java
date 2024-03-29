package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransfer;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferPackage;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {
    private final UserDao userDao;
    private final JdbcTransfer transferDao;
    private final JdbcAccountDao accountDao;

    public TransferController(UserDao userDao, JdbcTransfer transferDao, JdbcAccountDao accountDao) {
        this.userDao = userDao;
        this.transferDao = transferDao;
        this.accountDao = accountDao;
    }

    @RequestMapping(path = "transfers/{id}", method = RequestMethod.GET)
    public List<Transfer> getTransfersByUserId(@PathVariable int id){
        List<Transfer> transfer = transferDao.getAllTransfers(id);
        return transfer;
    }

    @RequestMapping(path = "transfer/{id}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable int id) {
        return transferDao.getTransferById(id);
    }

    @RequestMapping(path = "users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "transfers", method = RequestMethod.POST)
    public Transfer transferBucks(@RequestBody TransferPackage tp) {
        int fromId = accountDao.getAccountByUserId(tp.getSenderId());
        int toId = accountDao.getAccountByUserId(tp.getRecipientId());
        BigDecimal amount = tp.getAmount();

        if (accountDao.subtractBalanceById(tp.getSenderId(), tp.getAmount()) == null) {
            return null; //might need to throw exception/return 400/500 status
        }

        accountDao.addBalanceById(tp.getRecipientId(), tp.getAmount());
        return transferDao.createTransfer(amount, fromId, toId, 2, 2);
    }

}
