package com.lis.review.controller;

import com.lis.review.service.AiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试控制器
 * 用于测试千问模型是否正常工作
 */
@Slf4j
@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {
    
    private final AiService aiService;
    
    /**
     * 简单测试：发送一个问题给AI
     * 
     * @param question 问题内容
     * @return AI的回复
     */
    @GetMapping("/ask")
    public Map<String, Object> askQuestion(@RequestParam(defaultValue = "你好，请介绍一下你自己") String question) {
        log.info("收到测试问题: {}", question);
        
        Map<String, Object> result = new HashMap<>();
        try {
            long startTime = System.currentTimeMillis();
            String answer = aiService.generate(question);
            long endTime = System.currentTimeMillis();
            
            result.put("success", true);
            result.put("question", question);
            result.put("answer", answer);
            result.put("responseTime", (endTime - startTime) + "ms");
            
            log.info("AI回复成功，耗时: {}ms", endTime - startTime);
        } catch (Exception e) {
            log.error("AI调用失败", e);
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
    
    /**
     * POST方式测试：发送更复杂的问题
     * 
     * @param request 请求体，包含问题内容
     * @return AI的回复
     */
    @PostMapping("/ask")
    public Map<String, Object> askQuestionPost(@RequestBody Map<String, String> request) {
        String question = request.getOrDefault("question", "你好");
        return askQuestion(question);
    }
    
    /**
     * 测试医学检验场景
     * 
     * @return AI对医学检验结果的分析
     */
    @GetMapping("/medical")
    public Map<String, Object> testMedicalReview() {
        log.info("测试医学检验场景");
        
        String prompt = """
                作为一名专业的医学检验专家，请审核以下检验结果：
                
                患者ID: P001
                患者姓名: 张三
                检验项目: 白细胞计数
                检验值: 12.5 ×10^9/L
                参考范围: 3.5-9.5
                检验日期: 2026-03-02 10:30:00
                
                请提供：
                1. 结果是否异常的判断
                2. 异常程度评估（如果异常）
                3. 可能的临床意义
                4. 建议的后续处理
                
                请用专业但易懂的语言回答。
                """;
        
        Map<String, Object> result = new HashMap<>();
        try {
            long startTime = System.currentTimeMillis();
            String answer = aiService.generate(prompt);
            long endTime = System.currentTimeMillis();
            
            result.put("success", true);
            result.put("scenario", "医学检验结果审核");
            result.put("review", answer);
            result.put("responseTime", (endTime - startTime) + "ms");
            
            log.info("医学检验测试成功，耗时: {}ms", endTime - startTime);
        } catch (Exception e) {
            log.error("医学检验测试失败", e);
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 健康检查
     * 
     * @return 服务状态
     */
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "UP");
        result.put("service", "LIS AI Review System");
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
}
