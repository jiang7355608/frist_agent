package com.lis.review.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 检验结果实体类
 */
@Data
public class TestResult {
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 患者ID
     */
    private String patientId;
    
    /**
     * 患者姓名
     */
    private String patientName;
    
    /**
     * 检验项目
     */
    private String testItem;
    
    /**
     * 检验值
     */
    private BigDecimal testValue;
    
    /**
     * 单位
     */
    private String unit;
    
    /**
     * 参考范围
     */
    private String referenceRange;
    
    /**
     * 检验日期
     */
    private LocalDateTime testDate;
    
    /**
     * 状态：PENDING-待审核, REVIEWED-已审核
     */
    private String status = "PENDING";
    
    /**
     * AI审核结果
     */
    private String aiReviewResult;
    
    /**
     * AI审核时间
     */
    private LocalDateTime aiReviewTime;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
