package com.bbva.ether.secaas.authz.pdp.jsonprofile.converters;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Attribute;
import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Category;
import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Request;
import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.SupportedDataTypes;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.w3c.dom.Element;
import org.wso2.balana.attr.*;
import org.wso2.balana.attr.xacml3.XPathAttribute;
import org.wso2.balana.ctx.AbstractRequestCtx;
import org.wso2.balana.ctx.xacml3.RequestCtx;
import org.wso2.balana.xacml3.Attributes;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toSet;

public class RequestToRequestCtxConverter extends CustomConverter<Request, AbstractRequestCtx> {

    public AbstractRequestCtx convert(Request request, Type<? extends AbstractRequestCtx> destinationType, MappingContext ctx) {
        Set<Attributes> attributesSet = new HashSet<>();
        Set<Attributes> categoryAttrs = Stream.of(request.getCategory())
                .map(this::convertCategoryToAttributes)
                .collect(toSet());
        Set<Attributes> resourceAttrs = Stream.of(request.getResource())
                .map(this::convertCategoryToAttributes)
                .collect(toSet());
        Set<Attributes> actionAttrs = Stream.of(request.getAction())
                .map(this::convertCategoryToAttributes)
                .collect(toSet());
        Set<Attributes> accessSubjectAttrs = Stream.of(request.getAccessSubject())
                .map(this::convertCategoryToAttributes)
                .collect(toSet());
        Set<Attributes> intermediarySubjectAttrs = Stream.of(request.getIntermediarySubject())
                .map(this::convertCategoryToAttributes)
                .collect(toSet());
        Set<Attributes> codeBaseAttrs = Stream.of(request.getCodeBase())
                .map(this::convertCategoryToAttributes)
                .collect(toSet());
        Set<Attributes> requestingMachineAttrs = Stream.of(request.getRequestingMachine())
                .map(this::convertCategoryToAttributes)
                .collect(toSet());
        attributesSet.addAll(categoryAttrs);
        attributesSet.addAll(resourceAttrs);
        attributesSet.addAll(actionAttrs);
        attributesSet.addAll(accessSubjectAttrs);
        attributesSet.addAll(intermediarySubjectAttrs);
        attributesSet.addAll(codeBaseAttrs);
        attributesSet.addAll(requestingMachineAttrs);
        return new RequestCtx(attributesSet, null);
    }

    private <T extends Category> org.wso2.balana.xacml3.Attributes convertCategoryToAttributes(T category) {
        String id = category.getId();
        URI categoryId = URI.create(category.getCategoryId());
        Element content = null;
        try {
             content = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(category.getContent().getBytes()))
                    .getDocumentElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<org.wso2.balana.ctx.Attribute> attributesSet = Stream.of(category.getAttribute())
                .map(this::convertFromJsonAttributeToBalanaAttribute)
                .collect(toSet());
        return new Attributes(categoryId, content, attributesSet, id);
    }

    private org.wso2.balana.ctx.Attribute convertFromJsonAttributeToBalanaAttribute(Attribute attribute) {
        URI id = URI.create(attribute.getAttributeId());
        AttributeValue value = loadAttributeValue(attribute.getDataType(),attribute.getValue());
        URI type = Optional.ofNullable(value)
                .map(AttributeValue::getType)
                .orElse(null);
        return new org.wso2.balana.ctx.Attribute(id, type,null,null, singletonList(value),false,3);
    }

    private AttributeValue loadAttributeValue(String type, String value) {
        try {
            switch (SupportedDataTypes.getByValue(type)) {
                case STRING:
                    return StringAttribute.getInstance(value);
                case BOOLEAN:
                    return BooleanAttribute.getInstance(value);
                case INTEGER:
                    return IntegerAttribute.getInstance(value);
                case DOUBLE:
                    return DoubleAttribute.getInstance(value);
                case TIME:
                    return TimeAttribute.getInstance(value);
                case DATE:
                    return DateAttribute.getInstance(value);
                case DATE_TIME:
                    return DateTimeAttribute.getInstance(value);
                case DAY_TIME_DURATION:
                    return DayTimeDurationAttribute.getInstance(value);
                case YEAR_MONTH_DURATION:
                    return YearMonthDurationAttribute.getInstance(value);
                case ANY_URI:
                    return AnyURIAttribute.getInstance(value);
                case HEX_BINARY:
                    return HexBinaryAttribute.getInstance(value);
                case BASE_64_BINARY:
                    return Base64BinaryAttribute.getInstance(value);
                case RFC_822_NAME:
                    return RFC822NameAttribute.getInstance(value);
                case X500_NAME:
                    return X500NameAttribute.getInstance(value);
                case IP_ADDRESS:
                    return IPAddressAttribute.getInstance(value);
                case DNS_NAME:
                    return DNSNameAttribute.getInstance(value);
                case XPATH_EXPRESSION:
                    return XPathAttribute.getInstance(value, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
