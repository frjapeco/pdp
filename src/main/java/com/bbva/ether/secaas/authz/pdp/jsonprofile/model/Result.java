package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    @JsonProperty("Decision")
    private String decision;

    @JsonProperty("Status")
    private Status status;

    @JsonProperty("Obligations")
    private ObligationOrAdvice[] obligations;

    @JsonProperty("AssociatedAdvice")
    private ObligationOrAdvice[] associatedAdvice;

    @JsonProperty("Category")
    private Category[] category;

    @JsonProperty("PolicyIdentifierList")
    private PolicyIdentifierList[] policyIdentifierList;

}
