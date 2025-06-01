package com.fluxo.flowpay.service;

import com.fluxo.flowpay.objects.ObjectsClient;
import com.fluxo.flowpay.response.ObjectsResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectsService {

    @Autowired
    private ObjectsClient objectsClient;

    public ObjectsResponse callObjectsResponse(Long id){
        try {
            return objectsClient.getObjectById(id);
        }catch (FeignException feignException){
            throw new RuntimeException(feignException.getMessage());
        }
    }
}
