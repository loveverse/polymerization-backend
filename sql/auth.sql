use
`auth_dev`;

begin;
INSERT INTO `sys_user` (`user_name`, `nick_name`, `password`, `sex`, `status`, `phone_number`, `email`, `user_type`,
                        `create_time`, `update_time`, `valid`, `version`)
VALUES ('admin', 'admin',
        '$10$4HfGv2bhGE9lCarXin13kOG53aWgvVrluBMg0Nq7vL0ySzoJRH7le', -- SpringSecurity加密的'admin',
        'M', '1', '13800138000', 'zhangsan@example.com', '1', NOW(), NOW(), 1, 1);
commit;