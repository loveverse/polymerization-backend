use
    `auth_dev`;

BEGIN;
INSERT INTO `sys_user` (`user_name`, `nick_name`, `password`, `sex`, `status`, `phone_number`, `email`,
                        `create_time`, `update_time`, `valid`, `version`)
VALUES ('admin', 'admin',
        '$10$4HfGv2bhGE9lCarXin13kOG53aWgvVrluBMg0Nq7vL0ySzoJRH7le', -- SpringSecurity加密的'admin',
        'M', '1', '13800138000', 'zhangsan@example.com', NOW(), NOW(), 1, 1);
COMMIT;


BEGIN;
INSERT INTO `sys_role`
VALUES (1, 'admin', 'ROLE_ADMIN', '1', NOW(), NOW(), 1, 1);
COMMIT;

BEGIN;
INSERT INTO `sys_dict`
values (1, 'sex_type', '性别类型', null, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict`
values (2, 'user_status_type', '用户状态', null, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict`
values (3, 'common_status_type', '通用状态', null, NOW(), NOW(), 1, 1);
COMMIT;

BEGIN;
INSERT INTO `sys_dict_item`
values (1, 1, 'M', '男', 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict_item`
values (2, 1, 'W', '女', 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict_item`
values (3, 2, '0', '停用', 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict_item`
values (4, 2, '1', '启用', 0, NOW(), NOW(), 1, 1);
COMMIT;
