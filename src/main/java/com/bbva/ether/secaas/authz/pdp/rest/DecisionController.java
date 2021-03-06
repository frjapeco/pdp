package com.bbva.ether.secaas.authz.pdp.rest;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Result;
import com.bbva.ether.secaas.authz.pdp.rest.dto.DecisionRequest;
import com.bbva.ether.secaas.authz.pdp.rest.dto.DecisionResponse;
import com.bbva.ether.secaas.authz.pdp.services.DecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/decision")
public class DecisionController {

    @Autowired
    private DecisionService decisionService;

    @PostMapping
    public DecisionResponse evaluate(@RequestBody DecisionRequest request) {
        Result[] response = decisionService.evaluate(request.getRequest());
        return DecisionResponse.builder()
                .response(response)
                .build();
    }

}
