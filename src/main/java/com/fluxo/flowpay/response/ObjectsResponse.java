package com.fluxo.flowpay.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fluxo.flowpay.dto.DataDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectsResponse {

    private Long id;
    private String name;
    @JsonProperty("data")
    private DataDTO dataDTO;

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

    public DataDTO getDataDTO() {
        return dataDTO;
    }

    public void setDataDTO(DataDTO dataDTO) {
        this.dataDTO = dataDTO;
    }
}