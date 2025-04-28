package com.fluxo.flowpay.service;

import com.fluxo.flowpay.model.Entry;
import com.fluxo.flowpay.repository.EntryRepository;
import com.fluxo.flowpay.enums.EntryType;
import com.fluxo.flowpay.dto.DailyBalanceDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EntryServiceTest {

    @Mock
    private EntryRepository er;

    @InjectMocks
    private EntryService es;

    @Test
    void testCalculateDailyBalanceWithCreditAndDebit() {
        LocalDate today = LocalDate.now();

        Entry creditEntry = new Entry();
        creditEntry.setData(today);
        creditEntry.setType(EntryType.CREDIT);
        creditEntry.setAmount(new BigDecimal("100.00"));

        Entry debitEntry = new Entry();
        debitEntry.setData(today);
        debitEntry.setType(EntryType.DEBIT);
        debitEntry.setAmount(new BigDecimal("40.00"));

        when(er.findAll()).thenReturn(List.of(creditEntry, debitEntry));

        List<DailyBalanceDTO> result = es.calculateDailyBalance();

        assertEquals(1, result.size());
        DailyBalanceDTO dailyBalance = result.get(0);
        assertEquals(today, dailyBalance.getData());
        assertEquals(new BigDecimal("100.00"), dailyBalance.getTotalCredit());
        assertEquals(new BigDecimal("40.00"), dailyBalance.getTotalDebit());
        assertEquals(new BigDecimal("60.00"), dailyBalance.getFinalBalance());
    }

    @Test
    void testCalculateDailyBalanceWithOnlyCredits() {
        LocalDate today = LocalDate.now();

        Entry creditEntry1 = new Entry();
        creditEntry1.setData(today);
        creditEntry1.setType(EntryType.CREDIT);
        creditEntry1.setAmount(new BigDecimal("100.00"));

        Entry creditEntry2 = new Entry();
        creditEntry2.setData(today);
        creditEntry2.setType(EntryType.CREDIT);
        creditEntry2.setAmount(new BigDecimal("50.00"));

        when(er.findAll()).thenReturn(List.of(creditEntry1, creditEntry2));

        List<DailyBalanceDTO> result = es.calculateDailyBalance();

        assertEquals(1, result.size());
        DailyBalanceDTO dailyBalance = result.get(0);
        assertEquals(today, dailyBalance.getData());
        assertEquals(new BigDecimal("150.00"), dailyBalance.getTotalCredit());
        assertEquals(BigDecimal.ZERO, dailyBalance.getTotalDebit());
        assertEquals(new BigDecimal("150.00"), dailyBalance.getFinalBalance());
    }

    @Test
    void testCalculateDailyBalanceWithOnlyDebits() {
        LocalDate today = LocalDate.now();

        Entry debitEntry1 = new Entry();
        debitEntry1.setData(today);
        debitEntry1.setType(EntryType.DEBIT);
        debitEntry1.setAmount(new BigDecimal("40.00"));

        Entry debitEntry2 = new Entry();
        debitEntry2.setData(today);
        debitEntry2.setType(EntryType.DEBIT);
        debitEntry2.setAmount(new BigDecimal("30.00"));

        when(er.findAll()).thenReturn(List.of(debitEntry1, debitEntry2));

        List<DailyBalanceDTO> result = es.calculateDailyBalance();

        assertEquals(1, result.size());
        DailyBalanceDTO dailyBalance = result.get(0);
        assertEquals(today, dailyBalance.getData());
        assertEquals(BigDecimal.ZERO, dailyBalance.getTotalCredit());
        assertEquals(new BigDecimal("70.00"), dailyBalance.getTotalDebit());
        assertEquals(new BigDecimal("-70.00"), dailyBalance.getFinalBalance());
    }

    @Test
    void testCalculateDailyBalanceWithNoEntries() {
        LocalDate today = LocalDate.now();

        when(er.findAll()).thenReturn(List.of());

        List<DailyBalanceDTO> result = es.calculateDailyBalance();

        assertEquals(0, result.size());
    }
}