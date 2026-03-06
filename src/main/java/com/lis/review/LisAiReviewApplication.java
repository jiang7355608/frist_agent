package com.lis.review;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LIS 智能审核系统启动类
 */
@SpringBootApplication
@MapperScan("com.lis.review.mapper")
public class LisAiReviewApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(LisAiReviewApplication.class, args);
    }
}
