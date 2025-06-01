package com.fluxo.flowpay.controller;

import com.fluxo.flowpay.response.ObjectsResponse;
import com.fluxo.flowpay.service.ObjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectsController {

    @Autowired
    private ObjectsService objectsService;

    @GetMapping("/objects/id")
    public ObjectsResponse getObjectById(@RequestParam("id") Long id){
        return objectsService.callObjectsResponse(id);
    }
}
