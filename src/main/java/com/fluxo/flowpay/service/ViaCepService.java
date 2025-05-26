package com.fluxo.flowpay.service;

import com.fluxo.flowpay.controller.CepController;
import com.fluxo.flowpay.response.ViaCepResponse;
import com.fluxo.flowpay.viacep.ViaCepClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaCepService {

    private static final Logger LOG = LoggerFactory.getLogger(CepController.class);

    @Autowired
    private ViaCepClient viaCepClient;

    public ViaCepResponse callViaCepApi(String cep) {
        try {
            viaCepClient.getCep(cep);
        } catch (FeignException feignException) {
            LOG.error(feignException.getMessage());
        }
        return viaCepClient.getCep(cep);
    }
}
