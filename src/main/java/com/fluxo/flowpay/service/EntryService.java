package com.fluxo.flowpay.service;

import com.fluxo.flowpay.dto.DailyBalanceDTO;
import com.fluxo.flowpay.enums.EntryType;
import com.fluxo.flowpay.model.Entry;
import com.fluxo.flowpay.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    public Entry save(Entry entry){
        return entryRepository.save(entry);
    }


    public List<DailyBalanceDTO> calculateDailyBalance(){
        return entryRepository.findAll().stream()
                .collect(groupingBy(Entry::getData))
                .entrySet()
                .stream()
                .map(entry -> {
                    BigDecimal credits = entry.getValue().stream()
                            .filter(e -> e.getType().equals(EntryType.CREDIT))
                            .map(Entry::getAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    BigDecimal debits = entry.getValue().stream()
                            .filter(e -> e.getType().equals(EntryType.DEBIT))
                            .map(Entry::getAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    return new DailyBalanceDTO(
                            entry.getKey(),
                            credits,
                            debits,
                            credits.subtract(debits)
                    );
                }).collect(Collectors.toList());
    }
}