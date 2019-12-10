package com.bbva.ether.secaas.authz.pdp.jsonprofile.converters;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.DecisionTypes;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class BalanaDecisionToStringConverter extends CustomConverter<Integer, String> {

    public String convert(Integer source, Type<? extends String> destinationType, MappingContext ctx) {
        DecisionTypes decisionType = DecisionTypes.getByValue(source);
        switch (decisionType) {
            case PERMIT:
                return "Permit";
            case DENY:
                return "Deny";
            case INDETERMINATE:
                return "Indeterminate";
            case NOT_APPLICABLE:
                return "NotApplicable";
        }
        return null;
    }

}