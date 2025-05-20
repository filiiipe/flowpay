package com.fluxo.flowpay.controller;

import com.fluxo.flowpay.dto.ViaCepDto;
import com.fluxo.flowpay.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/busca/cep")
    public ViaCepDto getCep(@RequestParam("cep") String cep) {
        return cepService.getCep(cep);
    }
}
