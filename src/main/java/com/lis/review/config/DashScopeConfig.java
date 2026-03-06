package com.lis.review.config;

import com.alibaba.dashscope.aigc.generation.Generation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里千问配置类
 */
@Configuration
public class DashScopeConfig {
    
    @Value("${dashscope.api-key}")
    private String apiKey;
    
    @Value("${dashscope.model-name}")
    private String modelName;
    
    @Value("${dashscope.temperature}")
    private Double temperature;
    
    @Value("${dashscope.max-tokens}")
    private Integer maxTokens;
    
    @Bean
    public Generation generation() {
        // 设置 API Key
        System.setProperty("dashscope.api.key", apiKey);
        return new Generation();
    }
    
    @Bean
    public DashScopeProperties dashScopeProperties() {
        DashScopeProperties properties = new DashScopeProperties();
        properties.setApiKey(apiKey);
        properties.setModelName(modelName);
        properties.setTemperature(temperature);
        properties.setMaxTokens(maxTokens);
        return properties;
    }
}
