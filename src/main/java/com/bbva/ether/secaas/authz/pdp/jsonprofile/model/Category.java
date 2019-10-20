package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Category {

    @JsonProperty("Content")
    private String content;

    @JsonProperty("CategoryId")
    private String categoryId;

    @JsonProperty("Attribute")
    private Attribute[] attribute = new Attribute[0];

}
