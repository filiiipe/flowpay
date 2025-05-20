package com.fluxo.flowpay.viacep;

import com.fluxo.flowpay.dto.ViaCepDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "via-cep", url = "https://viacep.com.br/ws")
public interface ViaCepClient {


    @GetMapping("/{cep}/json/")
    ViaCepDto getCep(@PathVariable("cep") String cep);
}
