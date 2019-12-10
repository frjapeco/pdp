package com.bbva.ether.secaas.authz.pdp.jsonprofile.model;

public enum StandardCategories {

    RESOURCE("urn:oasis:names:tc:xacml:3.0:attribute-category:resource"),
    ACTION("urn:oasis:names:tc:xacml:3.0:attribute-category:action"),
    ENVIRONMENT("urn:oasis:names:tc:xacml:3.0:attribute-category:environment"),
    ACCESS_SUBJECT("urn:oasis:names:tc:xacml:1.0:subject-category:access-subject"),
    RECIPIENT_SUBJECT("urn:oasis:names:tc:xacml:1.0:subject-category:recipient-subject"),
    INTERMEDIARY_SUBJECT("urn:oasis:names:tc:xacml:1.0:subject-category:intermediary-subject"),
    CODEBASE("urn:oasis:names:tc:xacml:1.0:subject-category:codebase"),
    REQUESTING_MACHINE("urn:oasis:names:tc:xacml:1.0:subject-category:requesting-machine");

    private String value;

    StandardCategories(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

}
