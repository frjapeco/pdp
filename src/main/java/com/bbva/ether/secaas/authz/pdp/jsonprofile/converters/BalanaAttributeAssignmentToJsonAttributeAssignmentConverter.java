package com.bbva.ether.secaas.authz.pdp.jsonprofile.converters;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.AttributeAssignment;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class BalanaAttributeAssignmentToJsonAttributeAssignmentConverter extends CustomConverter<org.wso2.balana.ctx.AttributeAssignment, AttributeAssignment> {

    public AttributeAssignment convert(org.wso2.balana.ctx.AttributeAssignment source, Type<? extends AttributeAssignment> destinationType, MappingContext ctx) {
        return AttributeAssignment.builder()
                .attributeId(source.getAttributeId().toString())
                .issuer(source.getIssuer())
                .value(source.getContent())
                .dataType(source.getType().getFragment())
                .build();
    }

}
