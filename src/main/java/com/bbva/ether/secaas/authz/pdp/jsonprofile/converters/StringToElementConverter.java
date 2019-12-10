package com.bbva.ether.secaas.authz.pdp.jsonprofile.converters;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

@Component
public class StringToElementConverter extends CustomConverter<String, Element> {

    public Element convert(String source, Type<? extends Element> destinationType, MappingContext ctx) {
        try {
            return DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(source.getBytes()))
                    .getDocumentElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}