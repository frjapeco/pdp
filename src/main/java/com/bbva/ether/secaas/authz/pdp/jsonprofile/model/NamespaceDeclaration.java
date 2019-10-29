package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NamespaceDeclaration {

    @JsonProperty("Prefix")
    private String prefix;

    @JsonProperty("Namespace")
    private String namespace;

}
