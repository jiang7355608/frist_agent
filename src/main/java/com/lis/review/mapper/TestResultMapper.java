package com.lis.review.mapper;

import com.lis.review.model.TestResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 检验结果 Mapper 接口
 */
@Mapper
public interface TestResultMapper {
    
    /**
     * 插入检验结果
     */
    void insert(TestResult testResult);
    
    /**
     * 更新检验结果
     */
    void update(TestResult testResult);
    
    /**
     * 根据ID查询
     */
    TestResult findById(@Param("id") Long id);
    
    /**
     * 根据患者ID查询
     */
    List<TestResult> findByPatientId(@Param("patientId") String patientId);
    
    /**
     * 根据状态查询
     */
    List<TestResult> findByStatus(@Param("status") String status);
    
    /**
     * 查询所有
     */
    List<TestResult> findAll();
}
