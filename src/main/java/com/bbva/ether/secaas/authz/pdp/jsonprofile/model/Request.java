package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Request {

    @JsonProperty("ReturnPolicyIdList")
    private boolean returnPolicyIdList = false;

    @JsonProperty("CombinedDecision")
    private boolean combinedDecision = false;

    @JsonProperty("Category")
    private Category[] category = new Category[0];

    @JsonProperty("Resource")
    private Resource[] resource = new Resource[0];

    @JsonProperty("Action")
    private Action[] action = new Action[0];

    @JsonProperty("AccessSubject")
    private AccessSubject[] accessSubject = new AccessSubject[0];

    @JsonProperty("RecipientSubject")
    private RecipientSubject[] recipientSubject = new RecipientSubject[0];

    @JsonProperty("IntermediarySubject")
    private IntermediarySubject[] intermediarySubject = new IntermediarySubject[0];

    @JsonProperty("CodeBase")
    private CodeBase[] codeBase = new CodeBase[0];

    @JsonProperty("RequestingMachine")
    private RequestingMachine[] requestingMachine = new RequestingMachine[0];

}
