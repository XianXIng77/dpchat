CREATE TABLE `user` (
                        `id`          bigint AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
                        `username`    varchar(50)  NOT NULL UNIQUE COMMENT '登录账号',
                        `password`    varchar(255) NOT NULL COMMENT '密码',
                        `email`       varchar(100) UNIQUE COMMENT '邮箱',
                        `phone`       varchar(20) COMMENT '手机号',
                        `status`      tinyint DEFAULT 1 COMMENT '状态：1-正常 0-禁用',
                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='用户表';

INSERT INTO user (username, password, email, phone, status)
VALUES
    ('a',   '123456', 'alice@example.com', '13800000001', 1),
    ('bob',     'abcdef', 'bob@example.com',   '13800000002', 1),
    ('charlie', '111111', NULL,                '13800000003', 1),
    ('dave',    '888888', 'dave@example.com',  NULL,          0),
    ('eve',     'password', 'eve@example.com', '13800000005', 1);