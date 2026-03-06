package com.lis.review.service.impl;

import com.lis.review.mapper.TestResultMapper;
import com.lis.review.model.TestResult;
import com.lis.review.service.AiService;
import com.lis.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 检验结果审核服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    
    private final TestResultMapper testResultMapper;
    private final AiService aiService;
    
    @Override
    @Transactional
    public TestResult reviewTestResult(Long id) {
        TestResult testResult = testResultMapper.findById(id);
        if (testResult == null) {
            throw new RuntimeException("检验结果不存在，ID: " + id);
        }
        
        log.info("开始审核检验结果，ID: {}, 患者: {}", id, testResult.getPatientName());
        
        String prompt = buildReviewPrompt(testResult);
        String aiReview = aiService.generate(prompt);
        
        testResult.setAiReviewResult(aiReview);
        testResult.setAiReviewTime(LocalDateTime.now());
        testResult.setStatus("REVIEWED");
        testResult.setUpdatedAt(LocalDateTime.now());
        
        testResultMapper.update(testResult);
        
        log.info("审核完成，ID: {}", id);
        return testResult;
    }
    
    @Override
    public List<TestResult> getPendingResults() {
        return testResultMapper.findByStatus("PENDING");
    }
    
    @Override
    public TestResult saveTestResult(TestResult testResult) {
        testResult.setCreatedAt(LocalDateTime.now());
        testResult.setUpdatedAt(LocalDateTime.now());
        if (testResult.getStatus() == null) {
            testResult.setStatus("PENDING");
        }
        testResultMapper.insert(testResult);
        log.info("保存检验结果成功，ID: {}, 患者: {}", testResult.getId(), testResult.getPatientName());
        return testResult;
    }
    
    @Override
    public TestResult getResultById(Long id) {
        TestResult result = testResultMapper.findById(id);
        if (result == null) {
            throw new RuntimeException("检验结果不存在，ID: " + id);
        }
        return result;
    }
    
    @Override
    public List<TestResult> getResultsByPatientId(String patientId) {
        return testResultMapper.findByPatientId(patientId);
    }
    
    /**
     * 构建AI审核提示词
     */
    private String buildReviewPrompt(TestResult result) {
        return String.format("""
                作为一名专业的医学检验专家，请审核以下检验结果：
                
                患者ID: %s
                患者姓名: %s
                检验项目: %s
                检验值: %s %s
                参考范围: %s
                检验日期: %s
                
                请提供：
                1. 结果是否异常的判断
                2. 异常程度评估（如果异常）
                3. 可能的临床意义
                4. 建议的后续处理
                
                请用专业但易懂的语言回答。
                """,
                result.getPatientId(),
                result.getPatientName(),
                result.getTestItem(),
                result.getTestValue(),
                result.getUnit() != null ? result.getUnit() : "",
                result.getReferenceRange(),
                result.getTestDate()
        );
    }
}
