package com.bbva.ether.secaas.authz.pdp.jsonprofile.converters;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Result;
import com.bbva.ether.secaas.authz.pdp.orika.OrikaBeanMapper;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wso2.balana.ctx.ResponseCtx;

@Component
public class ResponseCtxToResultArrayConverter extends CustomConverter<ResponseCtx, Result[]> {

    @Autowired
    private OrikaBeanMapper mapper;

    public Result[] convert(ResponseCtx response, Type<? extends Result[]> destinationType, MappingContext ctx) {
        mapper.map(new org.wso2.balana.ctx.xacml3.Result(1, null), Result.class);
        return mapper.mapAsList(response.getResults(),Result.class).toArray(Result[]::new);
    }


}
