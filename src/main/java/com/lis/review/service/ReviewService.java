package com.lis.review.service;

import com.lis.review.model.TestResult;

import java.util.List;

/**
 * 检验结果审核服务接口
 */
public interface ReviewService {
    
    /**
     * 对检验结果进行AI审核
     * 
     * @param id 检验结果ID
     * @return 审核后的检验结果
     */
    TestResult reviewTestResult(Long id);
    
    /**
     * 获取待审核的检验结果列表
     * 
     * @return 待审核检验结果列表
     */
    List<TestResult> getPendingResults();
    
    /**
     * 保存检验结果
     * 
     * @param testResult 检验结果对象
     * @return 保存后的检验结果
     */
    TestResult saveTestResult(TestResult testResult);
    
    /**
     * 根据ID查询检验结果
     * 
     * @param id 检验结果ID
     * @return 检验结果
     */
    TestResult getResultById(Long id);
    
    /**
     * 根据患者ID查询检验结果
     * 
     * @param patientId 患者ID
     * @return 检验结果列表
     */
    List<TestResult> getResultsByPatientId(String patientId);
}
