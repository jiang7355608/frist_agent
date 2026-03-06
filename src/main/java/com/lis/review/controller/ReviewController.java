package com.lis.review.controller;

import com.lis.review.model.TestResult;

import com.lis.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 检验结果审核控制器
 * 提供检验结果的提交、审核、查询等功能
 * 
 * @author LIS Team
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;
    
    /**
     * 提交检验结果
     * 
     * @param testResult 检验结果对象，包含患者信息、检验项目、检验值等
     * @return 保存后的检验结果，包含生成的ID
     */
    @PostMapping("/submit")
    public ResponseEntity<TestResult> submitTestResult(@RequestBody TestResult testResult) {
        TestResult saved = reviewService.saveTestResult(testResult);
        return ResponseEntity.ok(saved);
    }
    
    /**
     * 对指定检验结果进行AI智能审核
     * 
     * @param id 检验结果ID
     * @return 审核后的检验结果，包含AI审核意见
     */
    @PostMapping("/{id}")
    public ResponseEntity<TestResult> reviewResult(@PathVariable Long id) {
        TestResult reviewed = reviewService.reviewTestResult(id);
        return ResponseEntity.ok(reviewed);
    }
    
    /**
     * 获取所有待审核的检验结果列表
     * 
     * @return 待审核检验结果列表
     */
    @GetMapping("/pending")
    public ResponseEntity<List<TestResult>> getPendingResults() {
        List<TestResult> results = reviewService.getPendingResults();
        return ResponseEntity.ok(results);
    }
    
    /**
     * 根据ID查询检验结果详情
     * 
     * @param id 检验结果ID
     * @return 检验结果详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<TestResult> getResult(@PathVariable Long id) {
        TestResult result = reviewService.getResultById(id);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 根据患者ID查询该患者的所有检验结果
     * 
     * @param patientId 患者ID
     * @return 该患者的检验结果列表
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<TestResult>> getResultsByPatient(@PathVariable String patientId) {
        List<TestResult> results = reviewService.getResultsByPatientId(patientId);
        return ResponseEntity.ok(results);
    }
}
