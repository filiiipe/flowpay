package com.fluxo.flowpay.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fluxo.flowpay.dto.Data;
import org.springframework.beans.factory.annotation.Autowired;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectsResponse {

    private Long id;
    private String name;

    @Autowired
    private Data data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}