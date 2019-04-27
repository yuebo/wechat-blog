package com.eappcat.spring.jdbc.bean;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class SecondaryDatasourceProperties {
    private Map<String, DataSourceProperties> secondary=new HashMap<>();
}
