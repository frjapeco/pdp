package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Attribute {

    @JsonProperty("AttributeId")
    private String attributeId;

    @JsonProperty("DataType")
    private String dataType;

    @JsonProperty("Issuer")
    private String issuer;

    @JsonProperty("IncludedInResult")
    private boolean includedInResult = false;

    @JsonProperty("Value")
    private String[] value = new String[0];

}
