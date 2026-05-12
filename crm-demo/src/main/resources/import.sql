-- 初始化示例客户数据
INSERT INTO customer (name, email, phone, company, status, created_at, updated_at) VALUES
('张三', 'zhangsan@example.com', '13800138000', '北京科技有限公司', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('李四', 'lisi@example.com', '13800138001', '上海贸易集团', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('王五', 'wangwu@example.com', '13800138002', '广州制造企业', 'INACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('赵六', 'zhaoliu@example.com', '13800138003', '深圳互联网公司', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('钱七', 'qianqi@example.com', '13800138004', '成都软件园', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
