use
    `auth_dev`;

BEGIN;
INSERT INTO `sys_user` (`user_name`, `nick_name`, `password`, `sex`, `status`, `phone_number`, `email`,
                        `create_time`, `update_time`, `valid`, `version`)
VALUES ('admin', 'admin',
        '$2a$10$KLG7eG6bAQJE0.rkBB/AfOMhKjBZr/3pTV5h8Iq0g5lHxUd/ww5xa', -- SpringSecurity加密的'admin',
        'M', '1', '13800138000', 'zhangsan@example.com', NOW(), NOW(), 1, 1);
COMMIT;


BEGIN;
INSERT INTO `sys_role`
VALUES (1, 'admin', 'ROLE_ADMIN', '1', NOW(), NOW(), 1, 1);
COMMIT;

BEGIN;
INSERT INTO `sys_dict`
values (1, 'sex_type', '性别类型', NOW(), NOW(), 1, 1, NULL);
INSERT INTO `sys_dict`
values (2, 'user_status_type', '用户状态', NOW(), NOW(), 1, 1, NULL);
INSERT INTO `sys_dict`
values (3, 'common_status_type', '通用状态', NOW(), NOW(), 1, 1, NULL);
INSERT INTO `sys_dict`
values (4, 'menu_type', '菜单类型', NOW(), NOW(), 1, 1, NULL);
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
INSERT INTO `sys_dict_item`
values (5, 4, '0', '目录', 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict_item`
values (6, 4, '1', '菜单', 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict_item`
values (7, 4, '2', '按钮', 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict_item`
values (8, 4, '3', '模块', 0, NOW(), NOW(), 1, 1);
COMMIT;
