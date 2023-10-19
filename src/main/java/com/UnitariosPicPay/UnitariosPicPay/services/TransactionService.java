package com.UnitariosPicPay.UnitariosPicPay.services;

import com.UnitariosPicPay.UnitariosPicPay.DTOs.TransactionDTO;
import com.UnitariosPicPay.UnitariosPicPay.domain.transactions.Transaction;
import com.UnitariosPicPay.UnitariosPicPay.domain.users.User;
import com.UnitariosPicPay.UnitariosPicPay.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    UserService userService;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    AuthorizationServices authorizationServices;

    @Autowired
    NotificationService notificationService;



     public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
         User sender = this.userService.findUserById(transactionDTO.sender());
         User receiver = this.userService.findUserById(transactionDTO.receiver());

         this.userService.validateUserTransaction(sender,transactionDTO.value());

         boolean isAuthorized = this.authorizationServices.authorizeTransaction(sender,transactionDTO.value());

         if (!isAuthorized){
             throw new Exception("transaction not authorized");
         }

         sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
         receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

         Transaction newTransaction = new Transaction();
         newTransaction.setSender(sender);
         newTransaction.setReceiver(receiver);
         newTransaction.setAmount(transactionDTO.value());
         newTransaction.setTimeStamp(LocalDateTime.now());

         this.userService.saveUser(sender);
         this.userService.saveUser(receiver);
         this.transactionsRepository.save(newTransaction);

         this.notificationService.sendNotification(sender,"transaction done successfully");
         this.notificationService.sendNotification(receiver,"transaction received successfully");
         return newTransaction;
     }

}
