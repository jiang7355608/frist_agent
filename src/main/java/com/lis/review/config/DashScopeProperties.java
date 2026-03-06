package com.lis.review.config;

import lombok.Data;

/**
 * 阿里千问配置属性
 */
@Data
public class DashScopeProperties {
    
    private String apiKey;
    private String modelName;
    private Double temperature;
    private Integer maxTokens;
}
