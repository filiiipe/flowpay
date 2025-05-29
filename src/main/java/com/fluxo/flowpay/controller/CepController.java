package com.fluxo.flowpay.controller;

import com.fluxo.flowpay.response.ViaCepResponse;
import com.fluxo.flowpay.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CepController {

    @Autowired
    private ViaCepService viaCepService;

    @GetMapping("/search/cep")
    public ViaCepResponse getCep(@RequestParam("cep") String cep) {
        return viaCepService.callViaCepApi(cep);
    }
}
