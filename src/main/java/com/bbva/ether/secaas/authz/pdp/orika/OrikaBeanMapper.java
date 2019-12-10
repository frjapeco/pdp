package com.bbva.ether.secaas.authz.pdp.orika;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class OrikaBeanMapper extends ConfigurableMapper implements ApplicationContextAware {

    private MapperFactory factory;

    private ApplicationContext applicationContext;

    public OrikaBeanMapper() {
        super(false);
    }

    protected void configure(MapperFactory factory) {
        this.factory = factory;
        applicationContext.getBeansOfType(Mapper.class).values().forEach(this::addMapper);
        applicationContext.getBeansOfType(Converter.class).values().forEach(this::addConverter);
    }

    protected void configureFactoryBuilder(DefaultMapperFactory.Builder factoryBuilder) { }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        init();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void addMapper(Mapper<?,?> mapper) {
        factory.classMap(mapper.getAType(), mapper.getBType())
                .byDefault()
                .customize((Mapper) mapper)
                .register();
    }

    private void addConverter(Converter<?,?> converter) {
        factory.getConverterFactory().registerConverter(converter);
    }

}
