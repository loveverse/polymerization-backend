use
`auth_dev`;

begin;
INSERT INTO `sys_user` (`user_name`, `nick_name`, `password`, `sex`, `status`, `phone_number`, `email`, `user_type`,
                        `create_time`, `update_time`, `valid`, `version`)
VALUES ('zhangsan', '张三',
        'e10adc3949ba59abbe56e057f20f883e', -- MD5加密的'123456',
        'M', '1', '13800138000', 'zhangsan@example.com', '1', NOW(), NOW(), 1, 1);
commit;