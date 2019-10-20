package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusCode {

    @JsonProperty("Value")
    private String value = "urn:oasis:names:tc:xacml:1.0:status:ok";

    @JsonProperty("StatusCode")
    private Object statusCode;

}
