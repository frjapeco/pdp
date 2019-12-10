package com.bbva.ether.secaas.authz.pdp.rest.dto;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Result;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionResponse {

    @JsonProperty("Response")
    private Result[] response;

}
