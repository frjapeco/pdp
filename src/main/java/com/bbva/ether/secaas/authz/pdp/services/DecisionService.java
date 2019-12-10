package com.bbva.ether.secaas.authz.pdp.services;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Request;
import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Result;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wso2.balana.PDP;
import org.wso2.balana.ctx.AbstractRequestCtx;
import org.wso2.balana.ctx.ResponseCtx;

@Service
public class DecisionService {

    @Autowired
    private PDP pdp;

    @Autowired
    private MapperFacade mapper;

    public Result[] evaluate(Request request) {
        AbstractRequestCtx requestCtx = mapper.map(request, AbstractRequestCtx.class);
        ResponseCtx responseCtx = pdp.evaluate(requestCtx);
        return mapper.map(responseCtx, Result[].class);
    }

}
