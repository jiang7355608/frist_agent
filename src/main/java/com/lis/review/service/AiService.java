package com.lis.review.service;

/**
 * AI服务接口
 */
public interface AiService {
    
    /**
     * 调用AI模型生成回复
     * 
     * @param prompt 提示词
     * @return AI生成的回复
     */
    String generate(String prompt);
}
