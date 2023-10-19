package com.UnitariosPicPay.UnitariosPicPay.DTOs;

import java.math.BigDecimal;

public record TransactionDTO(Long sender, Long receiver, BigDecimal value) {
}
