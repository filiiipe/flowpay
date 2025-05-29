package com.fluxo.flowpay.service;

import com.fluxo.flowpay.controller.ObjectsController;
import com.fluxo.flowpay.objects.ObjectsClient;
import com.fluxo.flowpay.response.ObjectsResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectsService {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectsController.class);
    @Autowired
    private ObjectsClient objectsClient;

    public ObjectsResponse callObjectsResponse(Long id){
        try {
            objectsClient.getObjectById(id);
        }catch (FeignException feignException){
            LOG.error(feignException.getMessage());
        }
        return objectsClient.getObjectById(id);
    }
}
