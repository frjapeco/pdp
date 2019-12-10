package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

import java.util.Arrays;

public enum SupportedDataTypes {

    STRING("string"),
    BOOLEAN("boolean"),
    INTEGER("integer"),
    DOUBLE("double"),
    TIME("time"),
    DATE("date"),
    DATE_TIME("dateTime"),
    DAY_TIME_DURATION("dayTimeDuration"),
    YEAR_MONTH_DURATION("yearMonthDuration"),
    ANY_URI("anyURI"),
    HEX_BINARY("hexBinary"),
    BASE_64_BINARY("base64Binary"),
    RFC_822_NAME("rfc822Name"),
    X500_NAME("x500Name"),
    IP_ADDRESS("ipAddress"),
    DNS_NAME("dnsName"),
    XPATH_EXPRESSION("xpathExpression");

    private String value;

    SupportedDataTypes(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public static SupportedDataTypes getByValue(String value) {
        return Arrays.stream(values())
                .filter(x -> x.value.equals(value))
                .findFirst()
                .orElse(STRING);
    }

}
