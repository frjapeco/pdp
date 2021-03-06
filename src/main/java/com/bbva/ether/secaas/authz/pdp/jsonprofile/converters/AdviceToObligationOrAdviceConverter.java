package com.bbva.ether.secaas.authz.pdp.jsonprofile.converters;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.AttributeAssignment;
import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.ObligationOrAdvice;
import com.bbva.ether.secaas.authz.pdp.orika.OrikaBeanMapper;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wso2.balana.xacml3.Advice;

@Component
public class AdviceToObligationOrAdviceConverter extends CustomConverter<Advice, ObligationOrAdvice> {

    @Autowired
    private OrikaBeanMapper mapper;

    public ObligationOrAdvice convert(Advice source, Type<? extends ObligationOrAdvice> destinationType, MappingContext ctx) {
        AttributeAssignment[] attributeAssignments = mapper.mapAsList(source.getAssignments(),AttributeAssignment.class)
                .toArray(AttributeAssignment[]::new);
        return ObligationOrAdvice.builder()
                .id(source.getAdviceId().toString())
                .attributeAssignment(attributeAssignments)
                .build();
    }

}
