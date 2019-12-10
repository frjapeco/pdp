package com.bbva.ether.secaas.authz.pdp.jsonprofile.converters;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Status;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;
import org.wso2.balana.ctx.StatusDetail;

import java.util.List;
import java.util.Optional;

@Component
public class BalanaStatusToJsonStatusConverter extends CustomConverter<org.wso2.balana.ctx.Status, Status> {

    public Status convert(org.wso2.balana.ctx.Status source, Type<? extends Status> destinationType, MappingContext ctx) {
        Object[] statusDetail = Optional.ofNullable(source.getDetail())
                .map(StatusDetail::getMissingAttributeDetails)
                .map(List::toArray)
                .orElse(null);
        return Status.builder()
                .statusMessage(source.getMessage())
                .statusDetail(statusDetail)
                .statusCode(source.getCode().stream().distinct().toArray(String[]::new))
                .build();
    }

}
