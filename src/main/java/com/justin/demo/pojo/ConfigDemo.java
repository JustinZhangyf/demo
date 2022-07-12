package com.justin.demo.pojo;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "author")
@PropertySource(value = {"classpath:rule.properties"},ignoreResourceNotFound = false,encoding = "UTF-8")
public class ConfigDemo {

    private List<String> name;

    private int age;

}
