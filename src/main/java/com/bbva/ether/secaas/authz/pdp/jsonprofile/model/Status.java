package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {

    @JsonProperty("StatusMessage")
    private String statusMessage;

    @JsonProperty("StatusDetail")
    private Object[] statusDetail;

    @JsonProperty("StatusCode")
    private String[] statusCode;

}
