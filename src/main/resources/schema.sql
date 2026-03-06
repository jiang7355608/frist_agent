-- 检验结果表
CREATE TABLE IF NOT EXISTS test_results (
    id BIGSERIAL PRIMARY KEY,
    patient_id VARCHAR(50) NOT NULL,
    patient_name VARCHAR(100) NOT NULL,
    test_item VARCHAR(100) NOT NULL,
    test_value DECIMAL(10, 2) NOT NULL,
    unit VARCHAR(20),
    reference_range VARCHAR(50),
    test_date TIMESTAMP NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    ai_review_result TEXT,
    ai_review_time TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建索引
CREATE INDEX IF NOT EXISTS idx_patient_id ON test_results(patient_id);
CREATE INDEX IF NOT EXISTS idx_test_date ON test_results(test_date);
CREATE INDEX IF NOT EXISTS idx_status ON test_results(status);
