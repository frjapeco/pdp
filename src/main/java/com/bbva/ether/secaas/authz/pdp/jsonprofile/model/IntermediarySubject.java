package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class IntermediarySubject extends Category {

    @JsonProperty("CategoryId")
    private String categoryId = StandardCategories.INTERMEDIARY_SUBJECT.toString();

}
