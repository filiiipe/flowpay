package com.fluxo.flowpay.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailyBalanceDTO {

    private LocalDate data;
    private BigDecimal totalCredit;
    private BigDecimal totalDebit;
    private BigDecimal finalBalance;

    public DailyBalanceDTO(){}

    public DailyBalanceDTO(LocalDate data, BigDecimal totalCredit, BigDecimal totalDebit, BigDecimal finalBalance) {
        this.data = data;
        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
        this.finalBalance = finalBalance;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(BigDecimal totalCredit) {
        this.totalCredit = totalCredit;
    }

    public BigDecimal getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(BigDecimal totalDebit) {
        this.totalDebit = totalDebit;
    }

    public BigDecimal getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(BigDecimal finalBalance) {
        this.finalBalance = finalBalance;
    }
}