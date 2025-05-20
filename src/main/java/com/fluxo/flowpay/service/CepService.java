package com.fluxo.flowpay.service;

import com.fluxo.flowpay.dto.ViaCepDto;
import com.fluxo.flowpay.viacep.ViaCepClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CepService {

    private static final Logger LOG = LoggerFactory.getLogger(CepService.class);

    @Autowired
    private ViaCepClient viaCepClient;

    public ViaCepDto getCep(String cep) {

        try {
            return viaCepClient.getCep(cep);
        } catch (FeignException exception) {
            LOG.error(exception.getMessage());
            throw new RuntimeException("Erro ao buscar o cep");
        }

    }
}
