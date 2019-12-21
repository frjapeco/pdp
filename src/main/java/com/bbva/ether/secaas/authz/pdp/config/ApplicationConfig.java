package com.bbva.ether.secaas.authz.pdp.config;

import com.bbva.ether.secaas.authz.pdp.balana.CustomAttributeFinder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wso2.balana.Balana;
import org.wso2.balana.PDP;
import org.wso2.balana.PDPConfig;
import org.wso2.balana.finder.AttributeFinder;
import org.wso2.balana.finder.AttributeFinderModule;

import java.util.List;

import static org.wso2.balana.finder.impl.FileBasedPolicyFinderModule.POLICY_DIR_PROPERTY;

@Configuration
public class ApplicationConfig {

    @Value("${balana.policies.location}")
    private String policiesLocation;

    @Bean
    public PDP pdp() {
        System.setProperty(POLICY_DIR_PROPERTY, policiesLocation);
        Balana balana = Balana.getInstance();
        PDPConfig pdpConfig = balana.getPdpConfig();
        AttributeFinder attributeFinder = pdpConfig.getAttributeFinder();
        List<AttributeFinderModule> finderModules = attributeFinder.getModules();
        finderModules.add(new CustomAttributeFinder());
        attributeFinder.setModules(finderModules);
        return new PDP(new PDPConfig(attributeFinder, pdpConfig.getPolicyFinder(), null, true));
    }

}
