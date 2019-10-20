package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class PolicyIdentifierList {

    @JsonPropertyOrder("Id")
    private String id;

    @JsonProperty("Version")
    private String version;

}
