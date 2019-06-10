package com.eappcat.spring.jdbc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.bind.PropertiesConfigurationFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
    public class DatasourceRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {
    private ConfigurableEnvironment environment;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment)environment;
    }
    private SecondaryDatasourceProperties resolveSettings() {
        SecondaryDatasourceProperties settings = new SecondaryDatasourceProperties();
        PropertiesConfigurationFactory<Object> factory = new PropertiesConfigurationFactory<Object>(settings);
        factory.setTargetName("spring.datasource");
        factory.setPropertySources(environment.getPropertySources());
        factory.setConversionService(environment.getConversionService());
        try {
            factory.bindPropertiesToTarget();
        }
        catch (BindException ex) {
            throw new FatalBeanException("Could not bind DataSourceSettings properties", ex);
        }
        return settings;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        SecondaryDatasourceProperties secondaryDatasourceProperties=resolveSettings();
        //设置主数据源为primary
        registry.getBeanDefinition("dataSource").setPrimary(true);
        if(secondaryDatasourceProperties.getSecondary()!=null){
            secondaryDatasourceProperties.getSecondary().forEach((name,datasource)->{
                //手动注册其他数据源
                BeanDefinitionBuilder builder=BeanDefinitionBuilder.genericBeanDefinition(DriverManagerDataSource.class).addPropertyValue("url",datasource.getUrl())
                        .addPropertyValue("username",datasource.getUsername())
                        .addPropertyValue("password",datasource.getPassword())
                        .addPropertyValue("driverClassName",datasource.getDriverClassName());

                registry.registerBeanDefinition(name.concat("Datasource"),builder.getBeanDefinition());

            });
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
