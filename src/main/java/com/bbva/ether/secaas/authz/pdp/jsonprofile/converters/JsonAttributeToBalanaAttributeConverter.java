package com.bbva.ether.secaas.authz.pdp.jsonprofile.converters;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Attribute;
import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.SupportedDataTypes;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;
import org.wso2.balana.attr.*;
import org.wso2.balana.attr.xacml3.XPathAttribute;

import java.net.URI;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Component
public class JsonAttributeToBalanaAttributeConverter extends CustomConverter<Attribute, org.wso2.balana.ctx.Attribute> {

    public org.wso2.balana.ctx.Attribute convert(Attribute source, Type<? extends org.wso2.balana.ctx.Attribute> destinationType, MappingContext ctx) {
        URI id = URI.create(source.getAttributeId());
        AttributeValue value = null;
        try {
            switch (SupportedDataTypes.getByValue(source.getDataType())) {
                case STRING:
                    value = StringAttribute.getInstance((String) source.getValue());
                    break;
                case BOOLEAN:
                    value = BooleanAttribute.getInstance((String) source.getValue());
                    break;
                case INTEGER:
                    value = IntegerAttribute.getInstance((String) source.getValue());
                    break;
                case DOUBLE:
                    value = DoubleAttribute.getInstance((String) source.getValue());
                    break;
                case TIME:
                    value = TimeAttribute.getInstance((String) source.getValue());
                    break;
                case DATE:
                    value = DateAttribute.getInstance((String) source.getValue());
                    break;
                case DATE_TIME:
                    value = DateTimeAttribute.getInstance((String) source.getValue());
                    break;
                case DAY_TIME_DURATION:
                    value = DayTimeDurationAttribute.getInstance((String) source.getValue());
                    break;
                case YEAR_MONTH_DURATION:
                    value = YearMonthDurationAttribute.getInstance((String) source.getValue());
                    break;
                case ANY_URI:
                    value = AnyURIAttribute.getInstance((String) source.getValue());
                    break;
                case HEX_BINARY:
                    value = HexBinaryAttribute.getInstance((String) source.getValue());
                    break;
                case BASE_64_BINARY:
                    value = Base64BinaryAttribute.getInstance((String) source.getValue());
                    break;
                case RFC_822_NAME:
                    value = RFC822NameAttribute.getInstance((String) source.getValue());
                    break;
                case X500_NAME:
                    value = X500NameAttribute.getInstance((String) source.getValue());
                    break;
                case IP_ADDRESS:
                    value = IPAddressAttribute.getInstance((String) source.getValue());
                    break;
                case DNS_NAME:
                    value = DNSNameAttribute.getInstance((String) source.getValue());
                    break;
                case XPATH_EXPRESSION:
                    XPathAttribute xPathAttribute = (XPathAttribute) source.getValue();
                    value = XPathAttribute.getInstance(xPathAttribute.getValue(), xPathAttribute.getXPathCategory());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        URI type = Optional.ofNullable(value).map(AttributeValue::getType).orElse(null);
        return new org.wso2.balana.ctx.Attribute(id, type,null,null, singletonList(value),false,3);
    }

}
