package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObligationOrAdvice {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("AttributeAssignment")
    private AttributeAssignment[] attributeAssignment;

}
