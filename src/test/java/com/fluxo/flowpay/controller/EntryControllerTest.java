package com.fluxo.flowpay.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fluxo.flowpay.dto.DailyBalanceDTO;
import com.fluxo.flowpay.enums.EntryType;
import com.fluxo.flowpay.model.Entry;
import com.fluxo.flowpay.service.EntryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("deprecation")
@WebMvcTest(EntryController.class)
class EntryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntryService entryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateEntry() throws Exception {
        Entry entry = new Entry();
        entry.setData(LocalDate.now());
        entry.setType(EntryType.CREDIT);
        entry.setAmount(new BigDecimal("150.00"));

        when(entryService.save(any(Entry.class))).thenReturn(entry);

        mockMvc.perform(post("/entries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entry)))
                .andExpect(status().isCreated())
                // ajustar para 150.0 em vez de 150.00
                .andExpect(jsonPath("$.amount").value(150.0))
                .andExpect(jsonPath("$.type").value("CREDIT"));
    }

    @Test
    void testGetDailyBalance() throws Exception {
        LocalDate today = LocalDate.now();
        DailyBalanceDTO balance = new DailyBalanceDTO(today,
                new BigDecimal("200.00"),
                new BigDecimal("50.00"),
                new BigDecimal("150.00"));

        when(entryService.calculateDailyBalance()).thenReturn(List.of(balance));

        mockMvc.perform(get("/entries/daily-balance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].data").value(today.toString()))
                .andExpect(jsonPath("$[0].totalCredit").value(200.0))
                .andExpect(jsonPath("$[0].totalDebit").value(50.0))
                .andExpect(jsonPath("$[0].finalBalance").value(150.0));
    }
}