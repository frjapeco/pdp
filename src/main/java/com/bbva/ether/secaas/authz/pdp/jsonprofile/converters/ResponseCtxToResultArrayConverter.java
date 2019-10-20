package com.bbva.ether.secaas.authz.pdp.jsonprofile.converters;

import com.bbva.ether.secaas.authz.pdp.jsonprofile.model.*;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.wso2.balana.ObligationResult;
import org.wso2.balana.ctx.AbstractResult;
import org.wso2.balana.ctx.ResponseCtx;
import org.wso2.balana.ctx.StatusDetail;
import org.wso2.balana.xacml3.Advice;
import org.wso2.balana.xacml3.Obligation;

import java.util.List;
import java.util.Optional;

public class ResponseCtxToResultArrayConverter extends CustomConverter<ResponseCtx, Result[]> {

    public Result[] convert(ResponseCtx response, Type<? extends Result[]> destinationType, MappingContext ctx) {
        return response.getResults()
                .stream()
                .map(this::convertAbstractResultToResult)
                .distinct()
                .toArray(Result[]::new);
    }

    private Result convertAbstractResultToResult(AbstractResult result) {
        return Result.builder()
                .decision(convertBalanaDecisionToString(result.getDecision()))
                .status(convertBalanaStatusToJsonStatus(result.getStatus()))
                .obligations(convertObligationListToObligationOrAdviceArray(result.getObligations()))
                .associatedAdvice(convertAdviceListToObligationOrAdviceArray(result.getAdvices()))
                .build();
    }

    private Status convertBalanaStatusToJsonStatus(org.wso2.balana.ctx.Status status) {
        Object[] statusDetail = Optional.ofNullable(status.getDetail())
                .map(StatusDetail::getMissingAttributeDetails)
                .map(List::toArray)
                .orElse(null);
        return Status.builder()
                .statusMessage(status.getMessage())
                .statusDetail(statusDetail)
                .statusCode(convertListStringToStatusCode(status.getCode()))
                .build();
    }

    private String[] convertListStringToStatusCode(List<String> codeList) {
        return codeList.stream().distinct().toArray(String[]::new);
    }

    private ObligationOrAdvice[] convertObligationListToObligationOrAdviceArray(List<ObligationResult> obligationList) {
        return obligationList.stream()
            .map(this::convertObligationToObligationOrAdvice)
            .distinct()
            .toArray(ObligationOrAdvice[]::new);
    }

    private ObligationOrAdvice convertObligationToObligationOrAdvice(ObligationResult obligationResult) {
        Obligation obligation = (org.wso2.balana.xacml3.Obligation) obligationResult;
        return ObligationOrAdvice.builder()
                .id(obligation.getObligationId().toString())
                .attributeAssignment(convertBalanaAttributeAssignmentListToJsonAttributeAssignmentArray(obligation.getAssignments()))
                .build();
    }


    private ObligationOrAdvice[] convertAdviceListToObligationOrAdviceArray(List<Advice> adviceList) {
        return adviceList.stream()
                .map(this::convertAdviceToObligationOrAdvice)
                .distinct()
                .toArray(ObligationOrAdvice[]::new);
    }

    private ObligationOrAdvice convertAdviceToObligationOrAdvice(Advice advice) {
        return ObligationOrAdvice.builder()
                .id(advice.getAdviceId().toString())
                .attributeAssignment(convertBalanaAttributeAssignmentListToJsonAttributeAssignmentArray(advice.getAssignments()))
                .build();
    }

    private AttributeAssignment[] convertBalanaAttributeAssignmentListToJsonAttributeAssignmentArray(List<org.wso2.balana.ctx.AttributeAssignment> attributeAssignmentList) {
        return attributeAssignmentList.stream()
            .map(this::convertBalanaAttributeAssignmentToJsonAttributeAssignment)
                .distinct()
                .toArray(AttributeAssignment[]::new);
    }

    private AttributeAssignment convertBalanaAttributeAssignmentToJsonAttributeAssignment(org.wso2.balana.ctx.AttributeAssignment attributeAssignment) {
        return AttributeAssignment.builder()
                .attributeId(attributeAssignment.getAttributeId().toString())
                .issuer(attributeAssignment.getIssuer())
                .value(attributeAssignment.getContent())
                .dataType(attributeAssignment.getType().getFragment())
                .build();
    }

    private String convertBalanaDecisionToString(int decision) {
        DecisionTypes decisionType = DecisionTypes.getByValue(decision);
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
