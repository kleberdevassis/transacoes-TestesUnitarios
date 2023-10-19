package com.UnitariosPicPay.UnitariosPicPay.repositories;

import com.UnitariosPicPay.UnitariosPicPay.domain.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transaction,Long> {
}
