package com.lis.review.service.impl;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.lis.review.config.DashScopeProperties;
import com.lis.review.service.AiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 阿里千问AI服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DashScopeAiServiceImpl implements AiService {
    
    private final Generation generation;
    private final DashScopeProperties properties;
    
    @Override
    public String generate(String prompt) {
        try {
            log.info("调用千问API，模型: {}", properties.getModelName());
            
            Message systemMessage = Message.builder()
                    .role(Role.SYSTEM.getValue())
                    .content("你是一名专业的医学检验专家，擅长分析和解读各类医学检验结果。")
                    .build();
            
            Message userMessage = Message.builder()
                    .role(Role.USER.getValue())
                    .content(prompt)
                    .build();
            
            GenerationParam param = GenerationParam.builder()
                    .model(properties.getModelName())
                    .messages(Arrays.asList(systemMessage, userMessage))
                    .temperature(properties.getTemperature().floatValue())
                    .maxTokens(properties.getMaxTokens())
                    .apiKey(properties.getApiKey())
                    .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                    .build();
            
            GenerationResult result = generation.call(param);
            
            String content = result.getOutput().getChoices().get(0).getMessage().getContent();
            log.info("千问API调用成功");
            
            return content;
            
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            log.error("调用千问API失败", e);
            throw new RuntimeException("AI审核失败: " + e.getMessage(), e);
        }
    }
}
