package com.bbva.ether.secaas.authz.pdp.jsonprofile.converters;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.ObligationOrAdvice;
import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Result;
import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Status;
import com.bbva.ether.secaas.authz.pdp.orika.OrikaBeanMapper;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BalanaResultToJsonResultConverter extends CustomConverter<org.wso2.balana.ctx.xacml3.Result, Result> {

    @Autowired
    private OrikaBeanMapper mapper;

    public Result convert(org.wso2.balana.ctx.xacml3.Result source, Type<? extends Result> destinationType, MappingContext ctx) {
        ObligationOrAdvice[] advices = mapper.mapAsList(source.getAdvices(), ObligationOrAdvice.class)
                .toArray(ObligationOrAdvice[]::new);
        ObligationOrAdvice[] obligations = mapper.mapAsList(source.getObligations(), ObligationOrAdvice.class)
                .toArray(ObligationOrAdvice[]::new);
        return Result.builder()
                .decision(mapper.map(source.getDecision(), String.class))
                .status(mapper.map(source.getStatus(), Status.class))
                .obligations(obligations)
                .associatedAdvice(advices)
                .build();
    }

}
