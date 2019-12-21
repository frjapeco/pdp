package com.bbva.ether.secaas.authz.pdp.balana;

import org.wso2.balana.attr.BagAttribute;
import org.wso2.balana.cond.EvaluationResult;
import org.wso2.balana.ctx.EvaluationCtx;
import org.wso2.balana.finder.AttributeFinderModule;

import java.net.URI;

public class CustomAttributeFinder extends AttributeFinderModule {

    public EvaluationResult findAttribute(URI attributeType, URI attributeId, String issuer, URI category, EvaluationCtx context) {
        return new EvaluationResult(BagAttribute.createEmptyBag(attributeType));
    }

}
