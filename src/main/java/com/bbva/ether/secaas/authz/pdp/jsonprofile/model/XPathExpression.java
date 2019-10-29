package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class XPathExpression {

    @JsonProperty("XPathCategory")
    private String xPathCategory;

    @JsonProperty("Namespaces")
    private NamespaceDeclaration[] namespaces;

    @JsonProperty("XPath")
    private String xPath;

}
