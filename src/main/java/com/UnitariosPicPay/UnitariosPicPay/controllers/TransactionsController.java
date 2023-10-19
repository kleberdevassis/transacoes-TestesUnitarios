package com.UnitariosPicPay.UnitariosPicPay.controllers;

import com.UnitariosPicPay.UnitariosPicPay.DTOs.TransactionDTO;
import com.UnitariosPicPay.UnitariosPicPay.DTOs.UserDTO;
import com.UnitariosPicPay.UnitariosPicPay.domain.transactions.Transaction;
import com.UnitariosPicPay.UnitariosPicPay.domain.users.User;
import com.UnitariosPicPay.UnitariosPicPay.services.TransactionService;
import com.UnitariosPicPay.UnitariosPicPay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User user = this.userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<User>> findAllUsers() {
        List usuarios = this.userService.findAllUsers();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @RequestMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = this.transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }


}
