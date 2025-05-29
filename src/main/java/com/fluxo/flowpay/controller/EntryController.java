package com.fluxo.flowpay.controller;

import com.fluxo.flowpay.dto.DailyBalanceDTO;
import com.fluxo.flowpay.model.Entry;
import com.fluxo.flowpay.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryController {


    @Autowired
    private EntryService entryService;

    @PostMapping
    public ResponseEntity<Entry> createEntry(@RequestBody Entry entry) {
        Entry savedEntry = entryService.save(entry);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntry);
    }

    @GetMapping("/daily-balance")
    public ResponseEntity<List<DailyBalanceDTO>> getDailyBalance() {
        List<DailyBalanceDTO> balances = entryService.calculateDailyBalance();
        return ResponseEntity.ok(balances);
    }
}