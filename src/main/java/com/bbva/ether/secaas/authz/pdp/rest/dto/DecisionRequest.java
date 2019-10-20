package com.bbva.ether.secaas.authz.pdp.rest.dto;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DecisionRequest {

    @JsonProperty("Request")
    private Request request;

}
