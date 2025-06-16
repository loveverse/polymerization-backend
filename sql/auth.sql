use
    `auth_dev`;

BEGIN;
INSERT INTO `sys_user`
VALUES (1, 'admin', 'admin',
        '$2a$10$KENIaQ4eKEvM7JLUl.HIluRmGRXeeFYEpz3x5ySW45.y.bj6p9WiC', -- SpringSecurity加密的'admin'
        'https://avatars.githubusercontent.com/u/64570135?v=4', 'M', '1', '13800138000', 'zhangsan@example.com',
        NOW(),
        NOW(), 1, 1);
INSERT INTO `sys_user`
VALUES (2, 'test', 'test', '$2a$10$KENIaQ4eKEvM7JLUl.HIluRmGRXeeFYEpz3x5ySW45.y.bj6p9WiC',
        'https://avatars.githubusercontent.com/u/64570135?v=4', 'M', '1', '13711111111', 'test@qq.com', NOW(), NOW(), 1,
        1);
COMMIT;

BEGIN;
INSERT INTO `sys_role`
VALUES (1, 'admin', 'ROLE_ADMIN', '1', NOW(), NOW(), 1, 1);
INSERT INTO `sys_role`
VALUES (2, 'test', 'ROLE_TEST', '1', NOW(), NOW(), 1, 1);
COMMIT;

BEGIN;
INSERT INTO `sys_user_role`
VALUES (1, 1);
INSERT INTO `sys_user_role`
VALUES (2, 2);
COMMIT;

BEGIN;
INSERT INTO `sys_module`
VALUES (1, '后台管理', 'm_ht', NULL, NULL, 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_module`
VALUES (2, '测试模块', 'm_test', NULL, NULL, 0, NOW(), NOW(), 1, 1);
COMMIT;

BEGIN;
INSERT INTO `sys_dict`
VALUES (1, 'gender_type', '性别类型', NULL, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict`
VALUES (2, 'user_status', '用户状态', NULL, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict`
VALUES (3, 'menu_type', '菜单类型', NULL, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict`
VALUES (4, 'common_status', '通用状态', NULL, NOW(), NOW(), 1, 1);
COMMIT;

BEGIN;
INSERT INTO `sys_dict_item`
VALUES (1, 1, 'M', '男', 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict_item`
VALUES (2, 1, 'W', '女', 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict_item`
VALUES (3, 2, '0', '停用', 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict_item`
VALUES (4, 2, '1', '启用', 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict_item`
VALUES (5, 3, '0', '目录', 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict_item`
VALUES (6, 3, '1', '菜单', 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict_item`
VALUES (7, 3, '2', '按钮', 0, NOW(), NOW(), 1, 1);
INSERT INTO `sys_dict_item`
VALUES (8, 3, '3', '模块', 0, NOW(), NOW(), 1, 1);
COMMIT;

BEGIN;
INSERT INTO `sys_menu`
VALUES (1000, 1, '模块管理', '/module', '1', 0, NULL, NULL, 1, 1000, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1001, 1, '模块新增', NULL, '2', 1000, NULL, 'sys:module:add', 1, 1001, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1002, 1, '模块删除', NULL, '2', 1000, NULL, 'sys:module:del', 1, 1002, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1003, 1, '模块修改', NULL, '2', 1000, NULL, 'sys:module:edit', 1, 1003, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1100, 1, '系统设置', '/system', '1', 0, NULL, NULL, 1, 1101, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1200, 1, '用户管理', '/system/user', '1', 1100, NULL, NULL, 1, 1200, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1201, 1, '用户新增', NULL, '2', 1200, NULL, 'sys:user:add', 1, 1201, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1202, 1, '用户删除', NULL, '2', 1200, NULL, 'sys:user:del', 1, 1202, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1203, 1, '用户修改', NULL, '2', 1200, NULL, 'sys:user:edit', 1, 1203, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1300, 1, '角色管理', '/system/role', '1', 1100, NULL, NULL, 1, 1300, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1301, 1, '角色新增', NULL, '2', 1300, NULL, 'sys:role:add', 1, 1301, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1302, 1, '角色删除', NULL, '2', 1300, NULL, 'sys:role:del', 1, 1302, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1303, 1, '角色修改', NULL, '2', 1300, NULL, 'sys:role:edit', 1, 1303, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1400, 1, '菜单管理', '/system/menu', '1', 1100, NULL, NULL, 1, 1400, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1401, 1, '菜单新增', NULL, '2', 1400, NULL, 'sys:menu:add', 1, 1401, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1402, 1, '菜单删除', NULL, '2', 1400, NULL, 'sys:menu:del', 1, 1402, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1403, 1, '菜单修改', NULL, '2', 1400, NULL, 'sys:menu:edit', 1, 1403, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1500, 1, '字典管理', '/system/dict', '1', 1100, NULL, NULL, 1, 1500, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1501, 1, '字典新增', NULL, '2', 1500, NULL, 'sys:dict:add', 1, 1501, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1502, 1, '字典删除', NULL, '2', 1500, NULL, 'sys:dict:del', 1, 1502, NOW(), NOW(), 1, 1);
INSERT INTO `sys_menu`
VALUES (1503, 1, '字典修改', NULL, '2', 1500, NULL, 'sys:dict:edit', 1, 1503, NOW(), NOW(), 1, 1);
COMMIT;
