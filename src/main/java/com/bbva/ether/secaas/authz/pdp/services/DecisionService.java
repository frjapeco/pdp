package com.bbva.ether.secaas.authz.pdp.services;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Request;
import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.Result;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wso2.balana.PDP;
import org.wso2.balana.ctx.AbstractRequestCtx;
import reactor.core.publisher.Mono;

@Service
public class DecisionService {

    @Autowired
    private PDP pdp;

    @Autowired
    private MapperFacade mapper;

    public Mono<Result[]> evaluate(Request request) {
        AbstractRequestCtx requestCtx = mapper.map(request, AbstractRequestCtx.class);
        // TODO: refactor this code when RxBalana is implemented
        return Mono.just(pdp.evaluate(requestCtx))
                .map(x -> mapper.map(x, Result[].class));
    }

}
