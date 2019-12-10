package com.bbva.ether.secaas.authz.pdp.jsonprofile.converters;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Request;
import com.bbva.ether.secaas.authz.pdp.orika.OrikaBeanMapper;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wso2.balana.ctx.AbstractRequestCtx;
import org.wso2.balana.ctx.xacml3.RequestCtx;
import org.wso2.balana.xacml3.Attributes;

import java.util.HashSet;
import java.util.Set;

@Component
public class RequestToRequestCtxConverter extends CustomConverter<Request, AbstractRequestCtx> {

    @Autowired
    private OrikaBeanMapper mapper;

    public AbstractRequestCtx convert(Request source, Type<? extends AbstractRequestCtx> destinationType, MappingContext ctx) {
        Set<Attributes> attributes = new HashSet<>();
        attributes.addAll(mapper.mapAsSet(source.getCategory(), Attributes.class));
        attributes.addAll(mapper.mapAsSet(source.getResource(), Attributes.class));
        attributes.addAll(mapper.mapAsSet(source.getAction(), Attributes.class));
        attributes.addAll(mapper.mapAsSet(source.getAccessSubject(), Attributes.class));
        attributes.addAll(mapper.mapAsSet(source.getIntermediarySubject(), Attributes.class));
        attributes.addAll(mapper.mapAsSet(source.getRecipientSubject(), Attributes.class));
        attributes.addAll(mapper.mapAsSet(source.getCodeBase(), Attributes.class));
        attributes.addAll(mapper.mapAsSet(source.getRequestingMachine(), Attributes.class));
        return new RequestCtx(attributes, null);
    }

}
