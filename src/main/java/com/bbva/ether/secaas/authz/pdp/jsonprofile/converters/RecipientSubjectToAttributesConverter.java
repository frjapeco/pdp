package com.bbva.ether.secaas.authz.pdp.jsonprofile.converters;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.RecipientSubject;
import com.bbva.ether.secaas.authz.pdp.orika.OrikaBeanMapper;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.wso2.balana.ctx.Attribute;
import org.wso2.balana.xacml3.Attributes;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

@Component
public class RecipientSubjectToAttributesConverter extends CustomConverter<RecipientSubject, Attributes> {

    @Autowired
    private OrikaBeanMapper mapper;

    public Attributes convert(RecipientSubject source, Type<? extends Attributes> destinationType, MappingContext ctx) {
        String id = source.getId();
        URI categoryId = URI.create(source.getCategoryId());
        Element content = Optional.ofNullable(source.getContent())
                .map(x -> mapper.map(x, Element.class))
                .orElse(null);
        Set<Attribute> attributes = mapper.mapAsSet(source.getAttribute(), Attribute.class);
        return new Attributes(categoryId, content, attributes, id);
    }

}
