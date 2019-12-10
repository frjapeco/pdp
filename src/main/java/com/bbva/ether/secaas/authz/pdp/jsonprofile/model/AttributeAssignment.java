package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttributeAssignment {

    @JsonProperty("AttributeId")
    private String attributeId;

    @JsonProperty("Value")
    private Object value;

    @JsonProperty("Category")
    private String category;

    @JsonProperty("DataType")
    private String dataType;

    @JsonProperty("Issuer")
    private String issuer;

}
