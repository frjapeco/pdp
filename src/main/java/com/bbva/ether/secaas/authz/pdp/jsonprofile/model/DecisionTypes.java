package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import java.util.Arrays;

public enum DecisionTypes {

    PERMIT(0),
    DENY(1),
    INDETERMINATE(2),
    NOT_APPLICABLE(3);

    private int value;

    DecisionTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DecisionTypes getByValue(int value) {
        return Arrays.stream(values())
                .filter(x -> x.value == value)
                .findFirst()
                .orElse(INDETERMINATE);
    }
}
