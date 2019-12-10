package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Category {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("CategoryId")
    private String categoryId;

    @JsonProperty("Content")
    private String content;

    @JsonProperty("Attribute")
    private Attribute[] attribute = new Attribute[0];

}
