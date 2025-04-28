package com.fluxo.flowpay.repository;

import com.fluxo.flowpay.model.Entry;
import com.fluxo.flowpay.enums.EntryType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Usa o H2
public class EntryRepositoryTest {

    @Autowired
    private EntryRepository er;

    @Test
    void testCreateAndReadEntry() {
        Entry entry = new Entry();
        entry.setData(LocalDate.now());
        entry.setType(EntryType.CREDIT);
        entry.setAmount(new BigDecimal("100.00"));

        Entry savedEntry = er.save(entry);

        Optional<Entry> retrievedEntry = er.findById(savedEntry.getId());

        assertTrue(retrievedEntry.isPresent());
        assertEquals(savedEntry.getData(), retrievedEntry.get().getData());
        assertEquals(savedEntry.getType(), retrievedEntry.get().getType());
        assertEquals(savedEntry.getAmount(), retrievedEntry.get().getAmount());
    }

    @Test
    void testUpdateEntry() {
        Entry entry = new Entry();
        entry.setData(LocalDate.now());
        entry.setType(EntryType.CREDIT);
        entry.setAmount(new BigDecimal("100.00"));
        Entry savedEntry = er.save(entry);

        savedEntry.setAmount(new BigDecimal("200.00"));
        Entry updatedEntry = er.save(savedEntry);

        assertEquals(new BigDecimal("200.00"), updatedEntry.getAmount());
    }

    @Test
    void testDeleteEntry() {
        Entry entry = new Entry();
        entry.setData(LocalDate.now());
        entry.setType(EntryType.DEBIT);
        entry.setAmount(new BigDecimal("50.00"));
        Entry savedEntry = er.save(entry);

        er.delete(savedEntry);

        Optional<Entry> deletedEntry = er.findById(savedEntry.getId());
        assertFalse(deletedEntry.isPresent());
    }
}