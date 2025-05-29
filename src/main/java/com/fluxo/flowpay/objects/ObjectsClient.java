package com.fluxo.flowpay.objects;

import com.fluxo.flowpay.response.ObjectsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "objects", url = "https://api.restful-api.dev")
public interface ObjectsClient {

    @GetMapping("objects/{id}/")
    ObjectsResponse getObjectById(@PathVariable("id") Long id);
}
